<#-- @ftlvariable name="" type="views.FollowingView" -->
<!DOCTYPE html>
<html lang="en" >

<head>

  <meta charset="UTF-8">

<link rel="apple-touch-icon" type="image/png" href="https://static.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" />
<meta name="apple-mobile-web-app-title" content="CodePen">

<link rel="shortcut icon" type="image/x-icon" href="https://static.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico" />

<link rel="mask-icon" type="" href="https://static.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111" />


  <title>CodePen - Simple List Hover Effect</title>




<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,600);

* {
  font-family: 'Open Sans', sans-serif;
}

h2 {
  text-align: center;
  font-weight: 400;
}

ul {
  list-style-type: none;
  width: 200px;
  height: auto;
  margin: 30px auto;
}

ul li {
  padding: 10px 0;
  border-bottom: 1px solid #add8e6;
  text-align: center;
  transition: margin-left 0.3s linear, font-weight 0.2s linear, color 0.3s linear;
  -webkit-transition: margin-left 0.3s linear, font-weight 0.2s linear, color 0.3s linear;
  -moz-transition: margin-left 0.3s linear, font-weight 0.2s linear, color 0.3s linear;
  -o-transition: margin-left 0.3s linear, font-weight 0.2s linear, color 0.3s linear;
  -ms-transition: margin-left 0.3s linear, font-weight 0.2s linear, color 0.3s linear;
}

ul li:first-child {
   border-top: 1px solid red;
}

ul li:hover {
  margin-left: 20px;
  font-weight: 600;
  color: #add8e6;
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
  <h2>Following List</h2>

<ul>
  <#list user.getFollowingChannels() as channel>
    <li><a href="/home/${user.name}/${user.email}/${channel.channelId}">${channel.channelName}</a></li>
  </#list>
  <li><a href="/home/${user.name}/${user.email}">Back to Home Page</a></li>
</ul>






</body>

</html>