<!DOCTYPE html>
<html lang="fr">
<!-- css files -->
<jsp:include page="newTemplate/staticFiles.jsp" />
<!-- /css files -->

  
  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
             <form action="<spring:url value='/j_spring_security_check' />" role="form" method="post">
              <h1>Login Form</h1>
              <div>
                <input type="text"  name="login" class="form-control" placeholder="Nom d'utilisateur" required="required" />
              </div>
              <div>
                 <input type="password" name="password" class="form-control" placeholder="mot de passe" required="required" />
              </div>
              <div>
                <a class="btn btn-default submit" href="index.html">Connexion</a>
                <a class="reset_pass" href="#">Mot de passe perdu?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Nouveau sur le site?
                  <a href="#signup" class="to_register"> Créer un compte </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa "></i> Gestion de Recouvrement</h1>
                  <p>copyright © 2016 Sharing Technologies. All Rights Reserved</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form action="<spring:url value='/j_spring_security_check' />" role="form" method="post">
              <h1>Create Account</h1>
              <div>
                <input type="text"  name="login" class="form-control" placeholder="Nom d'utilisateur" required="required" />
              </div>
              <div>
                <input  type="Email" name="mail" class="form-control" placeholder="Email" required="required" />
              </div>
              <div>
                <input type="password" name="password" class="form-control" placeholder="mot de passe" required="required" />
              </div>
              <div>
<!--                 <a class="btn btn-default submit" href="index.html">Submit</a> -->
                <input type="submit" name="Sign In" value="Login">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Déjà membre ?
                  <a href="#signin" class="to_register"> Log in </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  
                  <h1><i class="fa "></i> Gestion de Recouvrement</h1>
                  <p>copyright © 2016 Sharing Technologies. All Rights Reserved</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
