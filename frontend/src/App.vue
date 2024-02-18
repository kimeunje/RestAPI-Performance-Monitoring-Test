<script setup>
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import store from './scripts/store';

const id = sessionStorage.getItem('id');

if (id) {
  store.commit('setAccount', id);
}
</script>

<template>
  <Header />
  <RouterView />
  <Footer />
</template>

<style>
.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}
</style>
