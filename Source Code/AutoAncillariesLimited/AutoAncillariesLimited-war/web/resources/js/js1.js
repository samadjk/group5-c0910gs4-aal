/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// <![CDATA[
Cufon.replace('h1, h2, h3, h4, h5, h6', {
    hover: true
});
$(function(){
    $('div.port').hover( function(){
        $(this).stop().animate({
            backgroundColor: "#f2f2f2"
        }, 300);
    }, function(){
        $(this).stop().animate({
            backgroundColor: "#f9f9f9"
        }, 300);
    });
});
            // ]]>
