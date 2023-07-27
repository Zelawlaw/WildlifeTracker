
    // Add click event listener to list items
    $('.nav-tabs li').on('click', function () {
     // Remove 'active' class from all list items
             $('.nav-tabs li').removeClass('active');
             // Add 'active' class to the clicked list item
             $(this).addClass('active');
             // Get the href attribute of the clicked list item
             const href = $(this).find('a').attr('href');
             // Navigate to the URL specified in the href attribute
             window.location.href = href;
    });
