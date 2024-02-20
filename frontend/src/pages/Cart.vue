<script setup>
import axios from 'axios';
import { reactive } from 'vue';
import lib from '@/scripts/lib';

const state = reactive({
  items: []
})

const load = () => {
  axios.get("/api/cart/items").then(({ data }) => {
    state.items = data
  })
}

const remove = (itemId) => {
  axios.delete(`/api/cart/items/${itemId}`).then(() => {
    load()
  })
}

load()

</script>

<template>
  <div class="cart">
    <div class="container">
      <ul>
        <li v-for="(i, idx) in state.items" :key="idx">
          <img :src="i.imgPath" />
          <span class="name">{{ i.name }}</span>
          <span class="price">{{ lib.getNumberFormmated(i.price - i.price * i.discountPer / 100) }}</span>
          <i class="fa fa-trash" @click="remove(i.id)"></i>
        </li>
      </ul>
      <router-link to="/order" class="btn btn-primary">
        구입하기
      </router-link>
    </div>
  </div>
</template>

<style scoped>
.cart ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.cart ul li {
  border: 1px solid #eee;
  margin-top: 25px;
  margin-bottom: 25px;
}

.cart ul li img {
  width: 150px;
  height: 150px;
}

.cart ul li .name {
  margin-left: 25px;
}

.cart ul li .price {
  margin-left: 25px;
}

.cart ul li i {
  margin-right: 50px;
  float: right;
  margin-top: 60px;
  font-size: 20px;
}

.cart .btn {
  width: 300px;
  padding: 30px 50px;
  font-size: 20px;
  display: block;
  margin: 0 auto;
}
</style>