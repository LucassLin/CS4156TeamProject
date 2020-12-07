<#-- @ftlvariable name="" type="views.FollowingView" -->
<!DOCTYPE html>
<html lang="en" >

<head>

  <meta charset="UTF-8">

<link rel="apple-touch-icon" type="image/png" href="https://static.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png" />
<meta name="apple-mobile-web-app-title" content="CodePen">

<link rel="shortcut icon" type="image/x-icon" href="https://static.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico" />

<link rel="mask-icon" type="" href="https://static.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111" />

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>CodePen - Simple List Hover Effect</title>




<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,600);

* {
  font-family: 'Open Sans', sans-serif;
}


.container {
    max-width: 93.5rem;
    margin: 0 auto;
    padding: 0 2rem;
}

body {
  font-family: var(--font-family-sans-serif);
  background-image: linear-gradient(109.6deg, rgba(253, 199, 141, 1) 11.3%, rgba(249, 143, 253, 1) 100.2%);
  color: white;
  text-align: center;
}

h1 {
  color: white;
  font-size: 56px;
  text-align: center;
}

h2 {
  text-align: center;
  font-size: 32px;
}

.info-container {
  display: grid;
  grid-template-columns: auto auto auto;
  justify-content: center;
}

.info-item {
  border: none;
  padding: 35px;
  font-size: 24px;
  text-align: center;
}

.channel-container {
  display: grid;
  grid-template-columns: 24% 24% 24% 24%;
  justify-content: center;
}

.channel-item {
  border: none;
  padding: 30px;
  font-size: 24px;
  text-align: center;
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

  <h1>Welcome ${user.name} <a href="/home/${user.name}/${user.email}"><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal">HOME</i></a></h1>

  <div class="info-container">
    <div class="info-item"><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i> ${user.email}</div>
    <div class="info-item"><i class="fa fa-youtube fa-fw w3-margin-right w3-large w3-text-teal"></i> Following ${user.getFollowingChannelsCount()}</div>
    <div class="info-item"><i class="fa fa-heart fa-fw w3-margin-right w3-large w3-text-teal"></i> Interests ${user.getUserInterestsCount()}</div>
  </div>

  <h2><i class="fa fa-youtube fa-fw w3-margin-right w3-large w3-text-teal"></i> Following Channels</h2>

<div class="channel-container">
  <#list user.getFollowingChannels() as channel>
    <div class="channel-item">
         <a href="/home/${user.name}/${user.email}/${channel.channelId}">
             <img src="${channel.photoLink}" style="width:100%;">${channel.channelName}</img>
         </a>
    </div>
  </#list>
</div>

<#list user.getFollowingChannels() as channel>
<#list channel.tags as tag>
                    ${tag}
                    <br>
              </#list>
</#list>

</body>

</html>