<#-- @ftlvariable name="" type="views.LoginView" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="295502871422-fbcl69h81flfjth8pdecdicbblhc4199.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <style>
    html,body{
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
    }
    body {
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #17202A;
    }
    </style>
</head>
<body>
<h1>${welcomeWords}
    </p><br></p>
    <style>
        body {
            color: #ecedf3;
            font-size: xx-large;
        }
    </style>
</h1>

<div class="g-signin2" data-onsuccess="onSignIn"></div>
<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        var params = profile.getName() + "/" + profile.getEmail();
        location.href = "/home/" + params;
    }
</script>
</body>
</html>
