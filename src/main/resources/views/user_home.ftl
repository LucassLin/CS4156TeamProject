<#-- @ftlvariable name="" type="views.UserHomeView" -->
<!DOCTYPE html>
<html lang="en" >

<head>

    <meta charset="UTF-8">

    <link rel="apple-touch-icon" type="image/png" href="https://static.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" />
    <meta name="apple-mobile-web-app-title" content="CodePen">

    <link rel="shortcut icon" type="image/x-icon" href="https://static.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico" />

    <link rel="mask-icon" type="" href="https://static.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111" />


    <title>CodePen - #1336 - Profile Card</title>


    <link rel='stylesheet' href='https://www.littlesnippets.net/css/codepen-result.css'>

    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:300,400,600);
        .snip1336 {
            font-family: 'Roboto', Arial, sans-serif;
            position: relative;
            overflow: hidden;
            margin: 10px;
            min-width: 230px;
            max-width: 315px;
            width: 100%;
            color: #ffffff;
            text-align: left;
            line-height: 1.4em;
            background-color: #141414;
        }
        .snip1336 * {
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            -webkit-transition: all 0.25s ease;
            transition: all 0.25s ease;
        }
        .snip1336 img {
            max-width: 100%;
            vertical-align: top;
            opacity: 0.85;
        }
        .snip1336 figcaption {
            width: 100%;
            background-color: #141414;
            padding: 25px;
            position: relative;
        }
        .snip1336 figcaption:before {
            position: absolute;
            content: '';
            bottom: 100%;
            left: 0;
            width: 0;
            height: 0;
            border-style: solid;
            border-width: 55px 0 0 400px;
            border-color: transparent transparent transparent #141414;
        }
        .snip1336 figcaption a {
            padding: 5px;
            border: 1px solid #ffffff;
            color: #ffffff;
            font-size: 0.7em;
            text-transform: uppercase;
            margin: 10px 0;
            display: inline-block;
            opacity: 0.65;
            width: 47%;
            text-align: center;
            text-decoration: none;
            font-weight: 600;
            letter-spacing: 1px;
        }
        .snip1336 figcaption a:hover {
            opacity: 1;
        }
        .snip1336 .profile {
            border-radius: 50%;
            position: absolute;
            bottom: 100%;
            left: 25px;
            z-index: 1;
            max-width: 90px;
            opacity: 1;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
        }
        .snip1336 .follow {
            margin-right: 4%;
            border-color: #2980b9;
            color: #2980b9;
        }
        .snip1336 h2 {
            margin: 0 0 5px;
            font-weight: 300;
        }
        .snip1336 h2 span {
            display: block;
            font-size: 0.5em;
            color: #2980b9;
        }
        .snip1336 p {
            margin: 0 0 10px;
            font-size: 0.8em;
            letter-spacing: 1px;
            opacity: 0.8;
        }
    </style>

    <script>
        window.console = window.console || function(t) {};
    </script>



    <script>
        if (document.location.search.match(/type=embed/gi)) {
            window.parent.postMessage("resize", "*");
        }
    </script>


</head>

<body translate="no" >
<h1  class="snip1336">Welcome ${userProfile.name}!</h1>
<br>
<figure class="snip1336">
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/sample87.jpg" alt="sample87" />
    <figcaption>
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/profile-sample4.jpg" alt="profile-sample4" class="profile" />
        <h2>Hans Down<span>Engineer</span></h2>
        <p>I'm looking for something that can deliver a 50-pound payload of snow on a small feminine target. Can you suggest something? Hello...? </p>
        <a href="#" class="follow">Follow</a>
        <a href="#" class="info">More Info</a>
    </figcaption>
</figure>
<figure class="snip1336 hover"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/sample74.jpg" alt="sample74" />
    <figcaption>
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/profile-sample2.jpg" alt="profile-sample2" class="profile" />
        <h2>Wisteria Widget<span>Photographer</span></h2>
        <p>Calvin: I'm a genius, but I'm a misunderstood genius. Hobbes: What's misunderstood about you? Calvin: Nobody thinks I'm a genius.</p>
        <a href="#" class="follow">Follow</a>
        <a href="#" class="info">More Info</a>
    </figcaption>
</figure>
<figure class="snip1336"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/sample69.jpg" alt="sample69" />
    <figcaption>
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/331810/profile-sample5.jpg" alt="profile-sample5" class="profile" />
        <h2>Desmond Eagle<span>Accountant</span></h2>
        <p>If you want to stay dad you've got to polish your image. I think the image we need to create for you is "repentant but learning".</p>
        <a href="#" class="follow">Follow</a>
        <a href="#" class="info">More Info</a>
    </figcaption>
</figure>
<script src="https://static.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script id="rendered-js" >
    /* Demo purposes only */
    $(".hover").mouseleave(
        function () {
            $(this).removeClass("hover");
        });
    //# sourceURL=pen.js
</script>



</body>

</html>

