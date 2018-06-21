<template>
  <div id="login">
    <vs-card>
      <vs-card-body>
        <div class="info-media">
          <h1>Objectonderhoud dashboard</h1>
          <p>
            admin@ohw.nl + jager2
          </p>
        </div>
        <div class="form">
          <h1>Inloggen</h1>
          <div class="input-view">
            <vs-input :vs-valid.sync="valid.email" vs-success-text="Geldig e-mailformaat" vs-danger-text="Dit is geen geldig e-mailformaat" vs-label-placeholder="Email" vs-type="email" v-model="input.email" />
            <vs-input vs-label-placeholder="Wachtwoord" vs-type="password" v-model="input.password" />
            <vs-button v-on:click="login()" vs-color="primary" vs-icon="lock_open">Inloggen</vs-button>
          </div>
        </div>
      </vs-card-body>
    </vs-card>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data() {
      return {
        input: {
          email: "",
          password: ""
        },
        valid: {
          "email": true,
        }
      }
    },
    methods: {
      login() {
        if (this.input.email != "" && this.input.password != "") {
          if (this.input.email == 'admin@ohw.nl' && this.input.password == 'jager2') {
            localStorage.setItem('auth', 20080)
            this.$router.replace(this.$route.query.redirect || '/')
            location.reload();
          } else {
            this.$vs.notify({
              title: 'Fout',
              text: 'Email en/of wachtwoord zijn onjuist!',
              color: 'warning',
              icon: 'error',
              position: 'top-center'
            })
            console.log("Email en/of wachtwoord zijn onjuist!");
          }
        } else {
          this.$vs.notify({
            title: 'Fout',
            text: 'Geen gegevens ingevoerd.',
            color: 'warning',
            icon: 'error',
            position: 'top-center'
          })
          console.log("Geen gegevens ingevoerd.");
        }
      }
    }
  }
</script>

<style lang="scss">
  #login {
    max-width: 740px;
    width: 100%;
    display: block;
    padding-top: 4%;
    margin: 0 auto;
    .vs-card {
      border-radius: 5px;
      margin: 0!important;
      overflow: hidden;
      .con-vs-card-body {
        padding: 0px;
        background-color: #fff;
        .info-media {
          background-image: url(../assets/bgimg.png);
          background-size: cover;
          background-position: center center;
          width: 100%;
          padding: 38px 30px 32px 30px;
          h1 {
            font-weight: 300;
            font-size: 2.1em;
            display: inline-block;
            margin-top: 2em;
            color: #fff;
          }
          p {
            margin-top: 10px;
            font-weight: 400;
            color: #fff;
            opacity: 0.89;
          }
        }
        .form {
          padding: 30px;
          h1 {
            font-weight: 400;
            font-size: 2em;
            display: inline-block;
            margin: 0 0 12px 0;
            opacity: 0.86;
          }
          .input-view {
            display: flex;
            align-items: stretch;
            margin: 16px 16px 16px 0;
            .con-input {
              flex: 1 0 0;
              margin: 0 16px 0 0;
              input {
                background-color: #F5F7FA;
                font-size: 16px;
                border: none!important;
                height: 44px;
              }
              .label {
                font-size: 16px;
              }
              .placeholder {
                bottom: 13px;
              }
              .noPlaceholderLabel {
                bottom: 48px;
                font-size: 14px;
              }
            }
            .vs-btn {
              .text {
                font-size: 16px;
                padding: 9px 11px;
              }
            }
          }
        }
      }
    }
  }
</style>