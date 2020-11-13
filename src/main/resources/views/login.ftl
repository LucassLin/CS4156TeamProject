<#-- @ftlvariable name="" type="views.LoginView" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="295502871422-fbcl69h81flfjth8pdecdicbblhc4199.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>
    <h1>${welcomeWords}</h1>
    <div class="g-signin2" data-onsuccess="onSignIn"></div>
    <script>
        function onSignIn(googleUser) {
            var profile = googleUser.getBasicProfile();
            var id_token = googleUser.getAuthResponse().id_token;
            var params = profile.getName() + "/" + profile.getEmail();
            location.href = "/home/" + params;
        }
    </script>
<#--    <a href="#" onclick="signOut();">Sign out</a>
    <script>
        function signOut() {
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
                console.log('User signed out.');
            });
        }
    </script>-->
</body>
</html>
