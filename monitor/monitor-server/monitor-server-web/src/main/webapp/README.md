# Kite: Responsive Admin Template #

Kite is a multipurpose and fully responsive admin template. It is ideal for any type of admin applications from custom admin panels and dashboards, to eCommerce and CMS backends. It has a clean and intuitive layout that is easy to work with. Built with Bootstrap v3, it looks perfect on all major browsers, tablets, and phones. Kite comes with several ready pages you can start your work with, and a complete set of custom UI elements that will make it easy for you to adjust the template for your needs.  

### Key features ###

 * Built with Bootstrap v3
 * Fully responsive layout
 * Vanilla CSS and source LESS files included
 * Datatables plugin included
 * Chart.js plugin included
 * Functioning contact form included
 * Complete set of custom UI elements included
 * Clean and developer friendly code
 * Free updates
 * Free support

### Pages ###

 * UI elements documentation
 * Account: Profile
 * Account: Edit profile
 * Account: Inbox
 * Account: Sign In (and password recovery)
 * Account: Sign Up
 * Orders
 * FAQ’s
 * Contact us
 * Dummy page

### UI elements ###

 * Tables
 * Forms
 * Charts
 * Alerts
 * Buttons
 * Pagination
 * Panels
 * Progress bars
 * Tabs
 * Typography

Current release is 1.0.0. Buying this template now you become eligible to free download all of its future updates including new pages and neat options.

### Stay tuned ###

Follow us on Twitter and Facebook to instantly know of new templates and updates released:   
https://twitter.com/YevSim, https://www.facebook.com/simpleqode

If you like our works, feel free to contact us with new feature requests or ideas for future templates:   
http://simpleqode.com/#contact   
   
Your feedback would be highly appreciated.




# General instructions #

Template structure:

/assets/bootstrap -- original Bootstrap files
/assets/css -- compiled CSS files
/assets/less -- source LESS files
/assets/js -- custom JS scripts
/assets/plugins -- third-party plugins
/assets/img -- images
/assets/ico -- favicon image

Q: How do I create a new page?
A: You can start with dummy.html. It contains the general wireframe, which includes navbar, sidebar, and footer.

Q: How do I edit the styles?
A: You can either work with vanilla CSS or source LESS files included. For vanilla CSS, please open assets/css/styles.css. Source LESS files are located at assets/less/. If you work with LESS files, after the changes are done, you only need to compile the main styles.less file located at assets/less/styles.less. This is the main LESS file that imports all of the other LESS files (including original Bootstrap source LESS files). Please visit http://lesscss.org/usage/index.html#third-party-compilers to find out how you can compile LESS files to CSS.

Q: How do I change the color scheme?
A: Changing a color scheme is easy with LESS. Please open /assets/less/components/colors.less, change the value of the @brand-primary and @brand-accent variables (also light and dark versions of those) and recompile the main styles.less file.

Q: How do I set up the contact form?
A: 

This template contains a fully functioning PHP contact form with spam protection powered by reCaptcha. Note: The contact form will not work in your local environment without a server that supports PHP. In order to set up the contact form, please follow the steps below:

1) Open config.php and fill out the required information:

 - reCaptcha Site ($publickey) and Secret ($privatekey) keys

Please go to https://www.google.com/recaptcha/admin/create if you don't have the keys yet.

 - Sender name and email address ($mail_sender)

This is a name and email address you will see in the "From:" line of new emails you will receive.

 - Your email address ($to_email)

This is an email address new emails will be sent to.

 - Email subject ($mail_subject)

This is a subject of new emails you will receive.

2) Insert your reCaptcha Site key (see Step 1) in /contact.html:


```
#!html

<div class="g-recaptcha" data-sitekey="YOUR_SITE_KEY"></div>
<!-- (e.g. <div class="g-recaptcha" data-sitekey="09sdv0sf9v0sdf9b0df9b09dfb"></div>) -->
```

3) Save all files.




# Sources and credits #


### General files ###

*  Twitter Bootstrap

URL: http://getbootstrap.com/  
AUTHOR: @mdo and @fat  
LICENSE: MIT  


### Plugins ###

*  Font Awesome

URL: http://fontawesome.io/  
AUTHOR: Dave Gandy  
LICENSE: MIT  

* Chart.js

URL: http://www.chartjs.org/
AUTHOR: https://github.com/chartjs/Chart.js/graphs/contributors
LICENSE: MIT 

* CountTo

URL: https://github.com/mhuggins/jquery-countTo
AUTHOR: Matt Huggins
LICENSE: MIT

* Datatables

URL: https://www.datatables.net/
AUTHOR:  SpryMedia Ltd
LICENSE: MIT

* Perfect scrollbar

URL: https://noraesae.github.io/perfect-scrollbar/
AUTHOR: Hyunje Alex Jun
LICENSE: MIT


### Images ###

URL: https://www.flickr.com/photos/meunier_prof/galleries/72157632677105635/  
LICENSE: Creative Commons  



# Changelog #

Version 1.0.0 - May 06, 2016

* Initial release