"use strict";
$(document).ready(function () {
    $('#hello').click(function () {
        alert("hello");
    });
    $('#showAuthors').click(function () {
        $.getJSON('/api/authors', function (jd) {
            let i = 0;
            $('#authors').html('<p> Authors </p>');
            while (i < jd.length) {
                $('#authors').append('<p> ID: ' + jd[i].id + '</p>');
                $('#authors').append('<p> Name: ' + jd[i].name + '</p>');
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
                if(quantity > 1) {
                    $("#deleteButton" + id).attr("disabled",false);
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
            if (jd){
                $("#addButton" + id).data("quantity", quantity);
                $("#addButton" + id).data("total-cost", totalCost);
                $("#addButton" + id).data("all-total", allTotal);
                $("#deleteButton" + id).data("quantity", quantity);
                $("#deleteButton" + id).data("total-cost", totalCost);
                $("#deleteButton" + id).data("all-total", allTotal);
                $("#quantity" + id).html(quantity);
                $("#cost" + id).html(totalCost);
                $("#total").html(allTotal);
                if(quantity === 1) {
                    $("#deleteButton" + id).attr("disabled",true);
                }
            }
            else alert("Ou...That's an ERROR!!")
        });

        request.fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});