"use strict";
$(document).ready(function () {
    $('#hello').click(function () {
        alert("hello");
    });
    $('#showFilter').click(function () {
        if ($('#filter').attr("hidden") === "hidden") {
            if ($('#filterForm').is(':empty')) {
                $.getJSON('/api/authors', function (authors) {
                    $('#filterForm').empty();
                    $('#filterForm').html('<p>Author:</p>');
                    let i = 0;
                    let currAuthors = new Array();
                    outer: while (i < authors.length) {
                        for (let j = 0; j < currAuthors.length; j++) {
                            if (authors[i].name == currAuthors[j]) {
                                i++;
                                break outer;
                            }
                        }
                        $('#filterForm').append('<input type="checkbox" name="authors" value="' + authors[i].name + '" />' + authors[i].name + '<br />');
                        currAuthors.push(authors[i].name);
                        i++;
                    }
                    ;

                    $('#filterForm').append('<p></p>');
                    $('#filterForm').append('<p>Cost:</p>');
                    $('#filterForm').append(
                        '<div class="col-md-4 order-md-1">' +
                        '<input type="text" name="cost" value="2">' +
                        '</div>' +
                        '<div class="col-md-1 order-md-3">' +
                        '<input type="text" name="cost" value="3">' +
                        '</div>');
                    $('#filterForm').append('<input type="submit" value="Submit">');
                    $('#filterForm').append('<input type="reset" value="Reset">');
                });
            }
            $('#filter').attr("hidden", false);
        } else {
            $('#filter').attr("hidden", true);
        }
    });

    $('#filterForm').submit(function (e) {
        e.preventDefault();
        let allAuthors = document.getElementById('filterForm').authors,
            allCost = document.getElementById('filterForm').cost,
            currentCost = new Object(),
            checkedAuthors = new Object(),
            i = 0,
            j = 0,
            length = 0;
        while (i < allAuthors.length) {
            if (allAuthors[i].checked == true) {
                checkedAuthors[length] = allAuthors[i].value;
                length++;
            }
            i++;
        }
        if(allCost[0].value == "") {
            currentCost[0] = "0";
        }
        else {
            currentCost[0] = allCost[0].value;
        }
        if(allCost[1].value == "") {
            currentCost[1] = "2147483646";
        }
        else {
            currentCost[1] = allCost[1].value;
        }
        let arr = { Authors: checkedAuthors, Cost: currentCost };
        $.ajax({
            url: '/api/books/filter',
            type: 'POST',
            data: JSON.stringify(arr),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(books) {
                $('#books').empty();
                $('#books').html();
                i = 0;
                while (i < books.length) {
                    $('#books').append(
                        '<div class="col-md-4">' +
                        '<div class="card mb-4 shadow-sm">' +
                        '<img class="bd-placeholder-img card-img-top" src="' + books[i].firstImage + '" width="100%" height="225">' +
                        '<div class="card-body">' +
                        '<h6 class="mb-0">' + books[i].title + '</h6>' +
                        '<div class="mb-1 text-muted">' + books[i].author.name + '</div>' +
                        '<p class="card-text mb-auto">' + books[i].description + '</p>' +
                        '<a class="stretched-link" href="/store/book/' + books[i].id + '}">Watch more</a>' +
                        '</div>' +
                        '</div>' +
                        '</div>'
                    );
                    i++;
                }
            }
        });

        /*$.getJSON('/api/books', function (books) {
            let checked = false;
                    for (j = 0; j < length; j++) {
                        if (books[i].author.name == checkedAuthors[j]) {
                            checked = true;
                        }
                    }
                    if (checked) {

                    }
        });*/
    });

    $(".js-addBook").click(function () {
        let id = $(this).data('id'),
            quantity = $(this).data('quantity') + 1,
            cost = $(this).data('cost'),
            totalCost = $(this).data('total-cost'),
            allTotal = $(this).data('all-total');
        totalCost = totalCost + cost;
        allTotal = allTotal + cost;
        let jd = false;
        let request = $.post("/cart/quantity/" + id + "/" + quantity, function (json) {
            jd = Boolean(json);
        }, "json");

        request.done(function () {
            if (jd) {
                $("#addButton" + id).data("quantity", quantity);
                $("#addButton" + id).data("total-cost", totalCost);
                $("#addButton" + id).data("all-total", allTotal);
                $("#deleteButton" + id).data("quantity", quantity);
                $("#deleteButton" + id).data("total-cost", totalCost);
                $("#deleteButton" + id).data("all-total", allTotal);
                $("#quantity" + id).html(quantity);
                $("#cost" + id).html(totalCost);
                $("#total").html(allTotal);
                if (quantity > 1) {
                    $("#deleteButton" + id).attr("disabled", false);
                }
            } else alert("Ou...That's an ERROR!!")
        });

        request.fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });

    $(".js-deleteBook").click(function () {
        let id = $(this).data('id'),
            quantity = $(this).data('quantity') - 1,
            cost = $(this).data('cost'),
            totalCost = $(this).data('total-cost'),
            allTotal = $(this).data('all-total');
        totalCost = totalCost - cost;
        allTotal = allTotal - cost;
        let jd = false;
        let request = $.post("/cart/quantity/" + id + "/" + quantity, function (json) {
            jd = Boolean(json);
        }, "json");

        request.done(function () {
            if (jd) {
                $("#addButton" + id).data("quantity", quantity);
                $("#addButton" + id).data("total-cost", totalCost);
                $("#addButton" + id).data("all-total", allTotal);
                $("#deleteButton" + id).data("quantity", quantity);
                $("#deleteButton" + id).data("total-cost", totalCost);
                $("#deleteButton" + id).data("all-total", allTotal);
                $("#quantity" + id).html(quantity);
                $("#cost" + id).html(totalCost);
                $("#total").html(allTotal);
                if (quantity === 1) {
                    $("#deleteButton" + id).attr("disabled", true);
                }
            } else alert("Ou...That's an ERROR!!")
        });

        request.fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });

    if ($('[id^="deleteButton"]').length > 0) {
        $('[id^="deleteButton"]').each(function () {
            if ($(this).data("quantity") == 1) {
                let id = $(this).data("id");
                $("#deleteButton" + id).attr("disabled", true);
            }
        });
    }
});


/* let books,
             i = 0,
             j = 0;
         while (i < jd.length) {
             books = books.add(authors[i].books);
             i++;
         }
         $('#books').empty();
         $('#books').html();
         while (j < books.length) {
             $('#books').append(
                 '<div class="col-md-4">\\n\' +\n' +
                 '            \'                        <div class="card mb-4 shadow-sm">\\n\' +\n' +
                 '            \'                            <img class="bd-placeholder-img card-img-top"\\n\' +\n' +
                 '            \'                                 th:src="${' + books[j].getFirstImage().getLink() + '} width="100%" height="225">\\n\' +\n' +
                 '            \'                            <div class="card-body">\\n\' +\n' +
                 '            \'                                <h6 class="mb-0" th:text="${' + books[j].title + '}" />\\n\' +\n' +
                 '            \'                                <div class="mb-1 text-muted" th:text="${' + books[j].author.getName() + '}" />\\n\' +\n' +
                 '            \'                                <p class="card-text mb-auto" th:text="${' + books[j].getDescription() + '}"></p>\\n\' +\n' +
                 '            \'                                <a class="stretched-link" th:href="@{/store/book/' + books[j].id + '}">Watch more</a>\\n\' +\n' +
             '            \'                            </div>\\n\' +\n' +
             '            \'                        </div>\\n\' +\n' +
             '            \'                    </div>\\n\'');
         }*/


/*    {
            {


                $.getJSON('/api/authors', function (jd) {
                    let i = 0;
                    while (i < jd.length) {
                        $('#authors').append('<input type="checkbox" name="authors" value="' + jd[i].name + '" />' + jd[i].name + '<br />');
                        i++;
                    }
                    $('#authors').append('</div>');
                });
            }
            if($('#costs').is(':empty')) {
                $('#costs').empty();
                $('#costs').html('<p>Cost:</p>');
                $.getJSON('/api/books', function(books) {
                    let i = 0,
                        min = 0,
                        max = books[i].cost;
                    while (i < books.length) {
                        $('#costs').append('<input type="checkbox" name="costs" value="' + books[i].cost + '" />' + books[i].cost + '<br />');
                        i++;
                    }
                });
            }

        }
        else {

        }*/

