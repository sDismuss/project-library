"use strict";
$(document).ready(function () {
    $('#hello').click(function () {
        alert("hello");
    });
    $('#showFilter').click(function () {
        let $1 = $('#filter'),
            $2 = $('#filterForm');
        if ($1.attr("hidden") === "hidden") {
            if ($2.is(':empty')) {
                $.getJSON('/api/authors', function (authors) {
                    let content = "",
                        currAuthors = [],
                        i = 0;
                    $2.empty();
                    content = content + '<fieldset class="form-group">';
                    content = content + '<div class="row">';
                    content = content + '<div class="col-sm-1">Authors</div>';
                    content = content + '<div class="col-sm-10">';
                    outer: while (i < authors.length) {
                        for (let j = 0; j < currAuthors.length; j++) {
                            if (authors[i].name === currAuthors[j]) {
                                i++;
                                break outer;
                            }
                        }
                        content = content + '<div class="form-check">';
                        content = content + '<input type="checkbox" class="form-check-input" id="' + authors[i].name + '" name="authors" value="' + authors[i].name + '" />';
                        content = content + '<label class="form-check-label" for="' + authors[i].name + '">' + authors[i].name + '</label>';
                        content = content + '</div>';
                        currAuthors.push(authors[i].name);
                        i++;
                    }
                    content = content + '</div>';
                    content = content + '</div>';
                    content = content + '</fieldset>';

                    content = content + '<fieldset class="form-group">';
                    content = content + '<div class="row">';
                    content = content + '<div class="col-sm-1">Cost</div>';
                    content = content + '<div class="col-sm-10">';
                    content = content + '<div class="form-group row">';
                    content = content + '<label class="col-sm-1 col-form-label" for="min">Min</label>';
                    content = content + '<div class="col-sm-6">';
                    content = content + '<input type="text" class="form-control" name="min" id="min" value="">';
                    content = content + '<div class="invalid-feedback">Max less than min</div>';
                    content = content + '</div>';
                    content = content + '</div>';
                    content = content + '<div class="form-group row">';
                    content = content + '<label class="col-sm-1 col-form-label" for="max">Max</label>';
                    content = content + '<div class="col-sm-6">';
                    content = content + '<input type="text" class="form-control" name="max" id="max" value="">';
                    content = content + '<div class="invalid-feedback">Max less than min</div>';
                    content = content + '</div>';
                    content = content + '</div>';
                    content = content + '</div>';
                    content = content + '</div>';
                    content = content + '</fieldset>';

                    content = content + '<input id="submit" type="submit" value="Submit">';
                    content = content + '<input type="reset" value="Reset">';

                    $2.append(content);

                    $('#min').blur(function () {
                        let min = document.getElementById('filterForm').min,
                            max = document.getElementById('filterForm').max;
                        if (min.value !== "" && max.value !== "" && Number(min.value) > Number(max.value)) {
                            $('#min').attr('class', 'form-control is-invalid');
                            $('#submit').attr('disabled', 'disabled');
                        } else {
                            $('#min').attr('class', 'form-control');
                            $('#max').attr('class', 'form-control');
                            $('#submit').attr('disabled', false);
                        }
                    });
                    $('#max').blur(function () {
                        let min = document.getElementById('filterForm').min,
                            max = document.getElementById('filterForm').max;
                        if (max.value !== "" && min.value !== "" && Number(min.value) > Number(max.value)) {
                            $('#max').attr('class', 'form-control is-invalid');
                            $('#submit').attr('disabled', 'disabled');
                        } else {
                            $('#min').attr('class', 'form-control');
                            $('#max').attr('class', 'form-control');
                            $('#submit').attr('disabled', false);
                        }
                    });
                });
            }
            $1.attr("hidden", false);
        } else {
            $1.attr("hidden", true);
        }
    });

    $('#filterForm').submit(function (e) {
        e.preventDefault();
        let allAuthors = document.getElementById('filterForm').authors,
            min = document.getElementById('filterForm').min,
            max = document.getElementById('filterForm').max,
            currentCost = {},
            checkedAuthors = {},
            i = 0,
            j = 0,
            length = 0;
        while (i < allAuthors.length) {
            if (allAuthors[i].checked === true) {
                checkedAuthors[length] = allAuthors[i].value;
                length++;
            }
            i++;
        }
        if (min.value === "") {
            currentCost[0] = "0";
        } else {
            currentCost[0] = min.value;
        }
        if (max.value === "") {
            currentCost[1] = "2147483646";
        } else {
            currentCost[1] = max.value;
        }
        if (currentCost[1] < currentCost[0]) {
            alert("That's an error");
        } else {
            let arr = {Authors: checkedAuthors, Cost: currentCost};
            $.ajax({
                url: '/api/books/filter',
                type: 'POST',
                data: JSON.stringify(arr),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                async: false,
                success: function (books) {
                    $('#books').empty();
                    i = 0;
                    while (i < books.length) {
                        $('#books').append(
                            '<div class="col-md-2">' +
                            '<div class="card mb-2 shadow-sm">' +
                            '<img class="bd-placeholder-img card-img-top" src="' + books[i].firstImage + '" width="100%" >' +
                            '<div class="card-body">' +
                            '<h6 class="mb-0">' + books[i].title + '</h6>' +
                            '<div class="mb-1 text-muted">' + books[i].author.name + '</div>' +
                            '<p class="card-text mb-auto">' + books[i].cost + '$</p>' +
                            '<a class="stretched-link" href="/store/book/' + books[i].id + '}">See details</a>' +
                            '</div>' +
                            '</div>' +
                            '</div>'
                        );
                        i++;
                    }
                }
            });
        }
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
                let $1 = $("#addButton" + id);
                $1.data("quantity", quantity);
                $1.data("total-cost", totalCost);
                $1.data("all-total", allTotal);
                let $2 = $("#deleteButton" + id);
                $2.data("quantity", quantity);
                $2.data("total-cost", totalCost);
                $2.data("all-total", allTotal);
                $("#quantity" + id).html(quantity);
                $("#cost" + id).html(totalCost);
                $("#total").html(allTotal);
                if (quantity > 1) {
                    $2.attr("disabled", false);
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
                let $1 = $("#addButton" + id);
                $1.data("quantity", quantity);
                $1.data("total-cost", totalCost);
                $1.data("all-total", allTotal);
                let $2 = $("#deleteButton" + id);
                $2.data("quantity", quantity);
                $2.data("total-cost", totalCost);
                $2.data("all-total", allTotal);
                $("#quantity" + id).html(quantity);
                $("#cost" + id).html(totalCost);
                $("#total").html(allTotal);
                if (quantity === 1) {
                    $2.attr("disabled", true);
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
