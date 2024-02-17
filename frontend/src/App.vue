<script setup>
import Home from './pages/Home.vue';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
</script>

<template>
  <Header />
  <Home />
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
