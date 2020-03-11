"use strict";
$(document).ready(function () {
    $('#hello').click(function () {
        alert("hello");
    });
    $('#showFilter').click(function () {
        if ($('#filter').attr("hidden") === "hidden") {
            if ($('#filterForm').is(':empty')) {
                $('#filterForm').empty();
                $('#filterForm').html('<p>Author:</p>');
                $.getJSON('/api/authors', function (authors) {
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
        let allAuthors = document.getElementById('filterForm'),
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
        $('#books').empty();
        $('#books').html();
        $.getJSON('/api/books', function (books) {
            i = 0;
            while (i < books.length) {
                let checked = false;
                for (j = 0; j < length; j++) {
                    if (books[i].author.name == checkedAuthors[j]) {
                        checked = true;
                    }
                }
                if (checked) {
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
                }
                i++;
            }
        });
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

