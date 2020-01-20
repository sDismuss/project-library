"use strict";
$(document).ready(function() {
    $('#hi').click(function () {
        alert("Hello, World");
    });
    $('#showAuthors').click(function () {
        $.getJSON('/api/authors', function(jd) {
            let i = 0;
            $('#authors').html('<p> Authors </p>');
            while(i < jd.length) {
                $('#authors').append('<p> ID: ' + jd[i].id + '</p>');
                $('#authors').append('<p>Name: ' + jd[i].name + '</p>');
                i++;
            }
        });
    });
});

