"use strict";
$(document).ready(function () {
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
