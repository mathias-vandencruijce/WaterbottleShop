import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

let url;
const headers = { Accept: "application/json" };


export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    products: [],
    inCart: [],
    user: {
      isAuthenticated: false,
      name: "",
      email: "",
      idToken: "",
      accessToken: "",
      partner: false
    },
    endpoints: {
      login: process.env.VUE_APP_AUTH_URL,
      partnercheck: process.env.VUE_APP_BACKEND_PARTNERCHECK_URL,
      products: process.env.VUE_APP_PRODUCTS_URL,
      addProduct: process.env.BASE_URL + "addproduct"
    },
  },
  getters: {
    products: state => state.products,
    inCart: state => state.inCart,
  },
  mutations: {
    setProducts(state, payload) {
      state.products = payload;
    },
    addToCart(state, payload) {
      console.log(payload);
      state.inCart.push(payload);
    },
    removeFromCart(state, item) {
      state.inCart.splice(item, 1);
    },
    logout(state) {
      state.user.isAuthenticated = false;
      state.user.name = "";
      state.user.email ="";
      state.user.idToken ="";
      state.user.accessToken = "";
      state.user.partner = "";
    },
    login(state, payload) {
      state.user.isAuthenticated = true;
      state.user.name = payload.name;
      state.user.email =payload.email;
      state.user.idToken =payload.idToken;
      state.user.accessToken =payload.accessToken;
    },
    SET_PARTNER(state, partner) {
      state.user.partner = partner;
    },
    setUrls(state) {
      console.log(process.env.VUE_APP_AUTH_URL)
      state.endpoints.login = process.env.VUE_APP_AUTH_URL;
      state.endpoints.products = process.env.VUE_APP_PRODUCTS_URL;
      url = state.endpoints.products;
      console.log(process.env);
    }
  },
  actions: {
    async getProducts(state) {
      url = process.env.VUE_APP_PRODUCTS_URL;

      const products = await fetch(url, { headers });
      const prods = await products.json();
      state.commit("setProducts", prods);
      console.log(prods);
    },
    async checkPartner({ state, commit }) {
      let accessToken = state.user.accessToken;
      console.log(accessToken);
      console.log("checking partner access", state.endpoints.partnercheck);
      const AuthStr = 'Bearer '.concat(accessToken);
      const AuthHeader = { 'Authorization': AuthStr};
      console.log("AuthToken=",AuthHeader);
      let response = await fetch(state.endpoints.partnercheck, {
        method: 'GET',
        headers: {
          'Authorization': AuthStr }
      });
      console.log("partneraccess",response);
      if (response.ok) {
        commit('SET_PARTNER', true);
        console.log("TRUE");
      } else {
        commit('SET_PARTNER', false);
        console.log("FALSE");
      }
    },
    registerProduct({ state }, obj) {
      let productsurl = state.endpoints.products;
      console.log(productsurl);
      let accessToken = state.user.accessToken;
      const AuthStr = 'Bearer '.concat(accessToken);
      console.log(AuthStr);
      console.log(obj);
      axios(productsurl, {
        method: 'POST',
        headers: {
          'Accept': '*/*',
          'Content-Type': 'application/json',
          'Authorization': AuthStr
        },
        credentials: 'include',
        data: obj
      })
          .then(response => {
            console.log('Response:', response);
          })
          .catch((error) => {
            console.error('Error:', error);
          });
    },
    addFeedback({ state }, obj) {
      let feedbackUrl = process.env.VUE_APP_BACKEND_URL + "/addFeedback";
      console.log(feedbackUrl);
      console.log(state);
      let accessToken = state.user.accessToken;
      const AuthStr = 'Bearer '.concat(accessToken);
      axios(feedbackUrl, {
        method: 'POST',
        headers: {
          'Accept': '*/*',
          'Content-Type': 'application/json',
          'Authorization': AuthStr
        },
        credentials: 'include',
        data: obj
      })
          .then(response => {
            console.log('Response:', response);
          })
          .catch((error) => {
            console.error('Error:', error);
          });
    },
  },
  modules: {
  }
})