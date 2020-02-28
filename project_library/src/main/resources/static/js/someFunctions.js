"use strict";
$(document).ready(function () {
    $('#hi').click(function () {
        alert("Hello, World");
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
        let request = $.post("/library/cart/quantity/" + id + "/" + quantity, function (json) {
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
        let request = $.post("/library/cart/quantity/" + id + "/" + quantity, function (json) {
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


    $('.parent-container').magnificPopup({
        delegate: 'a',
        gallery: {
            enabled: true
        },
        type: 'image',
        callbacks: {
            elementParse: function (item) {
                console.log(item);
            }
        },
        closeOnContentClick: true
    });
});

let func = function () {
    $.post('/library/cart/quantity', function (json) {
        let t = false;
        t = json.toString();
        console.log(json.length);
    });
    return t;
};

$(function () {

});

/*


        alert(jd);*/