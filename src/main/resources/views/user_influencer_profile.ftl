<#-- @ftlvariable name="" type="views.UserInfluencerProfileView" -->
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <link rel="apple-touch-icon" type="image/png"
          href="https://static.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png"/>
    <meta name="apple-mobile-web-app-title" content="CodePen">

    <link rel="shortcut icon" type="image/x-icon"
          href="https://static.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico"/>

    <link rel="mask-icon" type=""
          href="https://static.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg"
          color="#111"/>


    <title>CodePen - Influencer Profile Page</title>


    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.8.2/css/all.css'>
    <link rel='stylesheet' href='https://unicons.iconscout.com/release/v2.0.1/css/unicons.css'>

    <style>
        :root {
            --bc: #efeeee;
            --white: #ffffff;
            --black: #000000;
            --dark-blue: #1f2029;
            --extra-dark-blue: #13141a;
            --dark-light: #424455;
            --red: #da2c4d;
            --dark-red: #6e1727;
            --yellow: #f8ab37;
            --grey: #ecedf3;
            --blue: #007bff;
            --indigo: #6610f2;
            --purple: #6f42c1;
            --pink: #e83e8c;
            --orange: #fd7e14;
            --green: #28a745;
            --light-green: #24e33a;
            --teal: #20c997;
            --cyan: #17a2b8;
            --gray: #6c757d;
            --primary: #d1a9de;
            --secondary: #7015b3;
            --success: #28a745;
            --info: #17a2b8;
            --warning: #ffc107;
            --danger: #dc3545;
            --font-family-sans-serif: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
            --font-family-monospace: SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
        }

        * {
            box-sizing: border-box;
        }

        html, body {
            margin: 0;
            width: 100%;
            min-height: 100vh;
            overflow-x: hidden;
            font-family: var(--font-family-sans-serif);
            scroll-behavior: smooth;
            -webkit-font-smoothing: antialiased;
            background-image: linear-gradient(109.6deg, rgba(253, 199, 141, 1) 11.3%, rgba(249, 143, 253, 1) 100.2%);
        }

        /* Classes */
        .space {
            margin: 1em 0;
        }

        /* User Header */
        .user-header-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .user-header-inner {
            width: 80%;
            min-height: 15vh;
            display: grid;
            align-items: center;
            grid-template-columns: auto 1fr;
            grid-column-gap: 2em;
            grid-row-gap: 2em;
        }

        .uh-left {
            width: 100%;
            height: 100%;
            padding: 0 2em 0 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .uh-image {
            width: 11rem;
            height: 11rem;
            border-radius: 50%;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
        }

        .uh-image-inner {
            width: 10.5rem;
            height: 10.5rem;
            border: .3em solid var(--grey);
            object-fit: cover;
            border-radius: 50%;
            z-index: 2;
        }

        .gradient {
            width: 100%;
            height: 100%;
            padding: .3em;
            position: absolute;
            border-radius: 50%;
            background: linear-gradient(45deg, rgba(214, 41, 118, 1) 0%, rgba(250, 126, 30, 1) 100%);
        }

        .gradient-gray {
            width: 100%;
            height: 100%;
            padding: .3em;
            position: absolute;
            border-radius: 50%;
            background: linear-gradient(45deg, rgba(236, 237, 243, 1) 0%, rgba(228, 229, 233, 1) 100%);
        }

        .uh-right {
            padding: 1em;
            width: 100%;
            height: 100%;
            display: flex;
            jusify-content: flex-start;
            flex-direction: column;
            align-items: flex-start;
        }

        .user-info {
            font-size: 2em;
            line-height: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .uname-verified {
            height: 1.8rem;
        }

        .btn {
            margin: 1em;
            border-radius: 3px;
            border: 1px solid var(--grey);
            background-color: var(--light);
            padding: .4em .6em;
            cursor: pointer;
            color: var(--black);
        }

        .user-links a {
            margin: 0 1em 0 0;
            cursor: pointer;
        }

        .user-links a span {
            color: var(--black);
            font-weight: bolder;
        }

        .user-bio-name {
            font-weight: bold;
            margin: 1.75em 0 0 0;
            line-height: 0;
        }

        /* User stories */
        .user-stories {
            margin: 3em 0 1em 0;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .user-stories-inner {
            width: 80%;
            display: grid;
            align-items: center;
            grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
            grid-column-gap: 4em;
        }

        .story-wrapper {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
        }

        .story-wrapper h2 {
            margin: .25em;
            font-size: 1.1em;
            font-weight: 400;
        }

        .story-inner {
            width: 75%;
            border-radius: 50%;
            overflow: hidden;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
        }

        .story-inner:after {
            content: "";
            display: block;
            padding-bottom: 100%;
        }

        .story-img {
            width: calc(100% - .5em);
            height: calc(100% - .5em);
            position: absolute;
            object-fit: cover;
            border-radius: 50%;
            border: 3px solid var(--grey);
            z-index: 2;
        }

        /* User Page */
        .user-page-wrapper {
            display: flex;
            justify-content: center;
            margin: 3em 0;
        }

        .user-page-inner {
            width: 80%;
            display: grid;
            align-items: center;
            grid-template-columns: 1fr 1fr 1fr;
            grid-template-rows: 1fr 1fr 1fr;
            grid-column-gap: 2em;
            grid-row-gap: 2em;
        }

        .image-wrapper {
            width: 100%;
            height: 100%;
            overflow: hidden;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
        }

        .image-wrapper:after {
            content: "";
            display: block;
            padding-bottom: 100%;
        }

        .image {
            width: 100%;
            height: 100%;
            position: absolute;
            object-fit: cover;
        }

        .img-overlay-wrapper {
            width: 100%;
            height: 100%;
            opacity: 0;
            position: absolute;
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 10;
            transition: opacity .15s ease-in-out;
        }

        .img-btns {
            font-size: 1.3em;
            color: var(--white);
            z-index: 10;
        }

        .img-overlay {
            width: 100%;
            height: 100%;
            position: absolute;
            background-color: rgba(0, 0, 0, .4);
            z-index: 9;
        }

        .image-block {
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
            position: fixed;
            display: none;
            justify-content: center;
            align-items: center;
            z-index: 20;
        }

        .block {
            width: 60%;
            height: 50%;
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: var(--light-mode-brig);
            z-index: 20;
        }

        .block-background {
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
            position: absolute;
            background-color: rgba(0, 0, 0, .4);
            z-index: 19;
        }

        /* User Loader */
        .user-loader {
            min-height: 25vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .loader {
            position: relative;
            width: 75px;
        }

        .circular {
            animation: rotate 2s linear infinite;
            height: 100%;
            width: 100%;
        }

        .path {
            stroke-dasharray: 1, 200;
            stroke-dashoffset: 0;
            animation: dash 1.5s ease-in-out infinite, color 6s ease-in-out infinite;
            stroke-linecap: round;
        }

        /* Keyframes */
        @keyframes rotate {
            100% {
                transform: rotate(360deg);
            }
        }

        @keyframes dash {
            0% {
                stroke-dasharray: 1, 200;
                stroke-dashoffset: 0;
            }
            50% {
                stroke-dasharray: 89, 200;
                stroke-dashoffset: -35px;
            }
            100% {
                stroke-dasharray: 89, 200;
                stroke-dashoffset: -124px;
            }
        }

        @keyframes color {
            100%, 0% {
                stroke: var(--red);
            }
            40% {
                stroke: var(--blue);
            }
            66% {
                stroke: var(--green);
            }
            80%, 90% {
                stroke: var(--yellow);
            }
        }

        @media (max-width: 1024px) {
            .user-header-inner {
                width: 85%;
            }

            .uh-image {
                width: 10rem;
                height: 10rem;
            }

            .uh-image-inner {
                width: 9.5rem;
                height: 9.5rem;
            }

            .user-stories-inner {
                width: 85%;
                grid-column-gap: 2em;
            }

            .story-wrapper {
                margin: 0 1em;
            }

            .user-page-inner {
                width: 85%;
                grid-column-gap: 1.5em;
                grid-row-gap: 1.5em;
            }

            .img-btns {
                font-size: 1.1em;
            }
        }

        @media (max-width: 936px) {
            .user-header-inner {
                width: 90%;
            }

            .uh-left {
                padding: 0 1em 0 0;
            }

            .uh-image {
                width: 9rem;
                height: 9rem;
            }

            .uh-image-inner {
                width: 8.5rem;
                height: 8.5rem;
            }

            .user-stories-inner {
                width: 90%;
                grid-column-gap: .75em;
            }

            .story-wrapper {
                margin: 0 .5em;
            }

            .user-page-inner {
                width: 90%;
                grid-column-gap: 1.25em;
                grid-row-gap: 1.25em;
            }

            .img-btns {
                font-size: 1em;
            }
        }

        @media (max-width: 767px) {
            .uh-left {
                padding: 0;
            }

            .uh-image {
                width: 8.5rem;
                height: 8.5rem;
            }

            .uh-image-inner {
                width: 8rem;
                height: 8rem;
            }

            .story-wrapper {
                margin: 0 .25em;
            }

            .user-page-inner {
                grid-column-gap: 1em;
                grid-row-gap: 1em;
            }

            .img-btns {
                font-size: .8em;
            }
        }
    </style>

    <script>
        window.console = window.console || function (t) {
        };
    </script>


    <script>
        if (document.location.search.match(/type=embed/gi)) {
            window.parent.postMessage("resize", "*");
        }
    </script>


</head>

<body translate="no">
<!--
Enjoy the code <3
-->
<div class="user-header-wrapper">
    <div class="user-header-inner">
        <div class="uh-left">
            <div class="uh-image">
                <img class="uh-image-inner" src=${userInfluencerProfile.influencer.photoLink} alt="">
                <div class="gradient"></div>
            </div>
        </div>
        <div class="uh-right">
            <div class="user-info">
                <h3>
                    ${userInfluencerProfile.influencer.channelName}
                    <svg class="uname-verified" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1350.03 1326.16">
                        <defs>
                            <style>.cls-11 {
                                    fill: var(--blue);
                                }

                                .cls-12 {
                                    fill: #ffffff;
                                }</style>
                        </defs>
                        <title>verified</title>
                        <g id="Layer_3" data-name="Layer 3">
                            <polygon class="cls-11"
                                     points="0 747.37 120.83 569.85 70.11 355.04 283.43 292.38 307.3 107.41 554.93 107.41 693.66 0 862.23 120.83 1072.57 126.8 1112.84 319.23 1293.35 399.79 1256.05 614.6 1350.03 793.61 1197.87 941.29 1202.35 1147.15 969.64 1178.48 868.2 1326.16 675.02 1235.17 493.77 1315.72 354.99 1133.73 165.58 1123.29 152.16 878.64 0 747.37"/>
                        </g>
                        <g id="Layer_2" data-name="Layer 2">
                            <path class="cls-12"
                                  d="M755.33,979.23s125.85,78.43,165.06,114c34.93-36,234.37-277.22,308.24-331.94,54.71,21.89,85,73.4,93,80.25-3.64,21.89-321.91,418.58-368.42,445.94-32.74-3.84-259-195.16-275.4-217C689.67,1049.45,725.24,1003.85,755.33,979.23Z"
                                  transform="translate(-322.83 -335.95)"/>
                        </g>
                    </svg>
                </h3>
            </div>
            <div class=user-links>
                <a><span>2.1k</span> Posts</a>
                <a><span>${userInfluencerProfile.influencer.numOfSubscribers}</span> Subscribers</a>
                <a>Following <span>388</span></a>
                <a>CountryCode <span>${userInfluencerProfile.influencer.countryCode}</span></a>
            </div>
            <div class="user-bio">
                <p class="user-bio-name">Areal Alien</p>
                <p>This is the user biography 😄<br/>It also has another line.</p>
                <br/>
                <#if userInfluencerProfile.liked == "Liked">
                    <a href="Like">${userInfluencerProfile.liked}</a>
                <#else>
                    <a href="Liked">${userInfluencerProfile.liked}</a>
                </#if>
            </div>
        </div>
    </div>
</div>
<div class="user-stories">
    <div class="user-stories-inner">
        <div class="story-wrapper">
            <div class="story-inner">
                <img class="story-img"
                     src="https://images.unsplash.com/photo-1588110679566-158c6dea107c?ixlib=rb-1.2.1&auto=format&fit=crop&w=1990&q=80"
                     alt="">
                <div class="gradient"></div>
            </div>
            <h2>Outfits ✌</h2>
        </div>
        <div class="story-wrapper">
            <div class="story-inner">
                <img class="story-img"
                     src="https://images.unsplash.com/photo-1470472304068-4398a9daab00?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80"
                     alt="">
                <div class="gradient"></div>
            </div>
            <h2>Travel 🛫</h2>
        </div>
        <div class="story-wrapper">
            <div class="story-inner">
                <img class="story-img"
                     src="https://images.unsplash.com/photo-1571997478779-2adcbbe9ab2f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2134&q=80"
                     alt="">
                <div class="gradient-gray"></div>
            </div>
            <h2>Food 🍕</h2>
        </div>
        <div class="story-wrapper">
            <div class="story-inner">
                <img class="story-img"
                     src="https://images.unsplash.com/photo-1586462175816-c0e709898f01?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjczMTc0fQ&auto=format&fit=crop&w=1950&q=80"
                     alt="">
                <div class="gradient-gray"></div>
            </div>
            <h2>Windlife 🌲</h2>
        </div>
        <div class="story-wrapper">
            <div class="story-inner">
                <img class="story-img"
                     src="https://images.unsplash.com/photo-1583314580204-efe0bcd18bc6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80"
                     alt="">
                <div class="gradient-gray"></div>
            </div>
            <h2>Relax 📚</h2>
        </div>
    </div>
</div>
<div class="user-page-wrapper">
    <div class="user-page-inner">
        <div id="imgblock1" class="image-block">
            <div class="block">
                <img class="image"
                     src="https://images.unsplash.com/photo-1559056986-f834be7896e5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1955&q=80"
                     alt="">
            </div>
            <div id="imgblockbc1" class="block-background"></div>
        </div>
        <div id="img1" class="image-wrapper">
            <div id="iov1" class="img-overlay-wrapper">
                <div class="img-btns">
                    <p>465 <i class="uil uil-heart-alt"></i> &nbsp&nbsp 25 <i class="uil uil-comment"></i></p>
                </div>
                <div class="img-overlay"></div>
            </div>
            <img class="image"
                 src="https://images.unsplash.com/photo-1559056986-f834be7896e5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1955&q=80"
                 alt="">
        </div>
    </div>
</div>

<div class="space"></div>
<script src="https://static.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>

<script id="rendered-js">
    $("#img1").on({
        mouseenter: function () {
            $("#iov1").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov1").css("opacity", "0");
        }
    });

    $('#img1').click(function () {

        $('#imgblock1').css("display", "flex");

    });
    $('#imgblockbc1').click(function () {

        $('#imgblock1').css("display", "none");

    });
    $("#img2").on({
        mouseenter: function () {
            $("#iov2").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov2").css("opacity", "0");
        }
    });

    $("#img3").on({
        mouseenter: function () {
            $("#iov3").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov3").css("opacity", "0");
        }
    });

    $("#img4").on({
        mouseenter: function () {
            $("#iov4").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov4").css("opacity", "0");
        }
    });

    $("#img5").on({
        mouseenter: function () {
            $("#iov5").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov5").css("opacity", "0");
        }
    });

    $("#img6").on({
        mouseenter: function () {
            $("#iov6").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov6").css("opacity", "0");
        }
    });

    $("#img7").on({
        mouseenter: function () {
            $("#iov7").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov7").css("opacity", "0");
        }
    });

    $("#img8").on({
        mouseenter: function () {
            $("#iov8").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov8").css("opacity", "0");
        }
    });

    $("#img9").on({
        mouseenter: function () {
            $("#iov9").css("opacity", "1");
        },
        mouseleave: function () {
            $("#iov9").css("opacity", "0");
        }
    });
    //# sourceURL=pen.js
</script>


</body>

</html>

