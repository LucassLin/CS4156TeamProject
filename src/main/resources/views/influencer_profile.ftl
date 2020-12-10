<#-- @ftlvariable name="" type="views.InfluencerProfileView" -->
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

        /**
 * Oscuro: #283035
 * Azul: #03658c
 * Detalle: #c7cacb
 * Fondo: #dee1e3
 ----------------------------------*/
        * {
            margin: 0;
            padding: 0;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        a {
            color: #03658c;
            text-decoration: none;
        }

        ul {
            list-style-type: none;
        }

        body {
            font-family: 'Roboto', Arial, Helvetica, Sans-serif, Verdana;
/*            background: #dee1e3;*/
        }

        /** ====================
         * Lista de Comentarios
         =======================*/
        .comments-container {
            margin: 60px auto 15px;
            width: 768px;
        }

        .comments-container h1 {
            font-size: 36px;
            color: #283035;
            font-weight: 400;
        }

        .comments-container h1 a {
            font-size: 18px;
            font-weight: 700;
        }

        .comments-list {
            margin-top: 30px;
            position: relative;
        }

        /**
         * Lineas / Detalles
         -----------------------*/
        .comments-list:before {
            content: '';
            width: 2px;
            height: 100%;
            background: #c7cacb;
            position: absolute;
            left: 32px;
            top: 0;
        }

        .comments-list:after {
            content: '';
            position: absolute;
            background: #c7cacb;
            bottom: 0;
            left: 27px;
            width: 7px;
            height: 7px;
            border: 3px solid #dee1e3;
            -webkit-border-radius: 50%;
            -moz-border-radius: 50%;
            border-radius: 50%;
        }

        .reply-list:before, .reply-list:after {display: none;}
        .reply-list li:before {
            content: '';
            width: 60px;
            height: 2px;
            background: #c7cacb;
            position: absolute;
            top: 25px;
            left: -55px;
        }


        .comments-list li {
            margin-bottom: 15px;
            display: block;
            position: relative;
        }

        .comments-list li:after {
            content: '';
            display: block;
            clear: both;
            height: 0;
            width: 0;
        }

        .reply-list {
            padding-left: 88px;
            clear: both;
            margin-top: 15px;
        }
        /**
         * Avatar
         ---------------------------*/
        .comments-list .comment-avatar {
            width: 65px;
            height: 65px;
            position: relative;
            z-index: 99;
            float: left;
            border: 3px solid #FFF;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            overflow: hidden;
        }

        .comments-list .comment-avatar img {
            width: 100%;
            height: 100%;
        }

        .reply-list .comment-avatar {
            width: 50px;
            height: 50px;
        }

        .comment-main-level:after {
            content: '';
            width: 0;
            height: 0;
            display: block;
            clear: both;
        }
        /**
         * Caja del Comentario
         ---------------------------*/
        .comments-list .comment-box {
            width: 680px;
            float: right;
            position: relative;
            -webkit-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
            -moz-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
            box-shadow: 0 1px 1px rgba(0,0,0,0.15);
        }

        .comments-list .comment-box:before, .comments-list .comment-box:after {
            content: '';
            height: 0;
            width: 0;
            position: absolute;
            display: block;
            border-width: 10px 12px 10px 0;
            border-style: solid;
            border-color: transparent #FCFCFC;
            top: 8px;
            left: -11px;
        }

        .comments-list .comment-box:before {
            border-width: 11px 13px 11px 0;
            border-color: transparent rgba(0,0,0,0.05);
            left: -12px;
        }

        .reply-list .comment-box {
            width: 610px;
        }
        .comment-box .comment-head {
            background: #FCFCFC;
            padding: 10px 12px;
            border-bottom: 1px solid #E5E5E5;
            overflow: hidden;
            -webkit-border-radius: 4px 4px 0 0;
            -moz-border-radius: 4px 4px 0 0;
            border-radius: 4px 4px 0 0;
        }

        .comment-box .comment-head i {
            float: right;
            margin-left: 14px;
            position: relative;
            top: 2px;
            color: #A6A6A6;
            cursor: pointer;
            -webkit-transition: color 0.3s ease;
            -o-transition: color 0.3s ease;
            transition: color 0.3s ease;
        }

        .comment-box .comment-head i:hover {
            color: #03658c;
        }

        .comment-box .comment-name {
            color: #283035;
            font-size: 14px;
            font-weight: 700;
            float: left;
            margin-right: 10px;
        }

        .comment-box .comment-name a {
            color: #283035;
        }

        .comment-box .comment-head span {
            float: left;
            color: #999;
            font-size: 13px;
            position: relative;
            top: 1px;
        }

        .comment-box .comment-content {
            background: #FFF;
            padding: 12px;
            font-size: 15px;
            color: #595959;
            -webkit-border-radius: 0 0 4px 4px;
            -moz-border-radius: 0 0 4px 4px;
            border-radius: 0 0 4px 4px;
        }

        .comment-box .comment-name.by-author, .comment-box .comment-name.by-author a {color: #03658c;}
        .comment-box .comment-name.by-author:after {
            content: 'autor';
            background: #03658c;
            color: #FFF;
            font-size: 12px;
            padding: 3px 5px;
            font-weight: 700;
            margin-left: 10px;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }

        /** =====================
         * Responsive
         ========================*/
        @media only screen and (max-width: 766px) {
            .comments-container {
                width: 480px;
            }

            .comments-list .comment-box {
                width: 390px;
            }

            .reply-list .comment-box {
                width: 320px;
            }
        }

        .clearfix::after {
            content: "";
            display: table;
            clear: both;
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
                <img class="uh-image-inner" src=${influencerProfile.photoLink} alt="">
                <div class="gradient"></div>
            </div>
        </div>
        <div class="uh-right">
            <div class="user-info">
                <h3>
                    ${influencerProfile.channelName}
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
                <a><span>${influencerProfile.numOfSubscribers}</span> Subscribers</a>
                <a>Following <span>388</span></a>
                <a>CountryCode <span>${influencerProfile.countryCode!"None"}</span></a>
            </div>
            <div class="user-bio">
                <p class="user-bio-name"></p>
                <p>${influencerProfile.description}</p>
            </div>
        </div>
    </div>
</div>
<div class="comments-container">
    <h1>Commments</h1>
    <ul id="comments-list" class="comments-list">
        <#list comments as comment>
            <li>
                <div class="comment-main-level">
                    <div class="comment-avatar"><img src="http://i9.photobucket.com/albums/a88/creaticode/avatar_2_zps7de12f8b.jpg" alt=""></div>
                    <div class="comment-box">
                        <div class="comment-head">
                            <h6 class="comment-name">${comment.email}</h6>
                            <i class="fa fa-reply"></i>
                            <i class="fa fa-heart"></i>
                        </div>
                        <div class="comment-content">
                            ${comment.comment}
                        </div>
                    </div>
                </div>
            </li>
        </#list>
    </ul>
    <div class="comment-main-level">
        <div class="comment-box">
            <div class="clearfix">
                <form id="comment-content" method="post" action="/CommentRecord/addRecord/${email}/${influencerProfile.channelId}">
                    <input type="text" id="comment-input" class="comment-input" name="comment" value="Enter your comment here">
                    <input class="comment-btn" type="submit" value="Send data">
                </form>
            </div>
        </div>
    </div>
</div>
<p align="center">
<#list videoLinks as videolink>
    <iframe width="460" height="315" src=${videolink}>
    </iframe>
</#list>
</p>

<div class="space"></div>
<script src="https://static.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>

</body>

</html>