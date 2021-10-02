<template>
  <nav class="navbar navbar-expand-sm navbar-dark bg-dark" role="navigation">
    <div class="container mw-100" >
      <ul class="navbar-nav mr-auto" >
        <router-link to="/" class="navbar-brand mr-4">Cloud 9</router-link>
        <router-link to="/store" class="navbar-brand mr-4">Store</router-link>
        <router-link to="/partners" class="navbar-brand mr-4">Partners</router-link>
        <router-link v-if="isAuthenticated" to="/contact" class="navbar-brand mr-4">Contact</router-link>
      </ul>
      <ul class="nav navbar-nav">
        <router-link to="/" tag="li" v-if="!isAuthenticated" class="nav-item" active-class="active">
          <a @click="onLoginClicked" class="nav-link">Login</a>
        </router-link>
        <li v-if="isAuthenticated" class="li-pointer nav-item">
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              {{ getUserName() }}
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#">Account Settings</a>
              <a v-if="isPartner" @click="onRegisterClicked" class="dropdown-item" href="#">Register Product</a>
              <a @click="onLogoutClicked" class="dropdown-item">Logout {{ userEmail }}</a>
            </div>
         </div>
        </li>
        <li>
          <ShoppingCart />
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import ShoppingCart from './ShoppingCart.vue';
export default {
  components: { ShoppingCart },
  name: 'NavHeader',
  computed: {
    userEmail() {
      return this.isLoggedIn ? this.currentUser.email : ''
    },
    isAuthenticated() {
      return this.$store.state.user.isAuthenticated;
    },
    isPartner() {
      return this.$store.state.user.partner;
    },
  },
  methods: {
    onLoginClicked() {
      window.location = this.$store.state.endpoints.login;
    },
    onLogoutClicked() {
      this.$store.commit("logout");
    },
    onRegisterClicked() {
      window.location = this.$store.state.endpoints.addProduct;
    },
    getUserName() {
      return this.$store.state.user.name;
    }
  }
}
</script>