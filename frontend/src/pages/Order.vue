<script setup>
import {computed, reactive, ref} from 'vue';
import router from '@/scripts/router';
import axios from 'axios';
import lib from '@/scripts/lib';

const state = reactive({
  items: [],

})

const form = ref({
  name: "",
  address: "",
  payment: "",
  cardNumber: "",
  items: "",
})

const computedPrice = computed(() => {
  let result = 0;
  for (let i of state.items) {
    result += i.price - i.price * i.discountPer / 100
  }

  return result;
})

const load = () => {
  axios.get("/api/cart/items").then(({data}) => {
    state.items = data;
    console.log(data);
  })
}

const submit = () => {
  const args = JSON.parse(JSON.stringify(form.value))
  args.items = JSON.stringify(state.items)
  axios.post("/api/orders", args).then(() => {
    alert('주문 완료하였습니다.')
    router.push('/orders')
  })
}

load()
</script>

<template>
  <div class="container">
    <main>
      <div class="py-5 text-center">
        <h2>주문하기</h2>
      </div>
      <div class="row g-5">
        <div class="col-md-5 col-lg-4 order-md-last">
          <h4 class="d-flex justify-content-between align-items-center mb-3"><span class="text-primary">구입
              목록</span><span class="badge bg-primary rounded-pill">{{ state.items.length }}</span></h4>
          <ul class="list-group mb-3">
            <li class="list-group-item d-flex justify-content-between lh-sm" v-for="(i, idx) in state.items" :key="idx">
              <div>
                <h6 class="my-0">{{ i.name }}</h6>
              </div>
              <span class="text-muted">{{ lib.getNumberFormmated(i.price - i.price * i.discountPer / 100) }}원</span>
            </li>
          </ul>
          <hr class="my-4">

          <h3 class="text-center totla-price">
            최종 가격 <span>{{ lib.getNumberFormmated(computedPrice) }}원</span>
          </h3>
        </div>
        <div class="col-md-7 col-lg-8">
          <h4 class="mb-3">주문자 정보</h4>
          <div class="needs-validation" novalidate="">
            <div class="row g-3">
              <div class="col-12"><label for="username" class="form-label">이름</label>
                <input type="text" class="form-control" id="username" placeholder="Username" v-model="form.name">

              </div>
              <div class="col-12"><label for="address" class="form-label">주소</label><input type="text"
                                                                                           class="form-control"
                                                                                           id="address"
                                                                                           placeholder="1234 Main St"
                                                                                           v-model="form.address">
                <div class="invalid-feedback"> Please enter your shipping address.</div>
              </div>
            </div>
            <hr class="my-4">
            <h4 class="mb-3">결제 수단</h4>
            <div class="my-3">
              <div class="form-check"><input id="card" name="paymentMethod" type="radio" class="form-check-input"
                                             value="card" v-model="form.payment"><label class="form-check-label"
                                                                                        for="card">신용 카드</label>
              </div>
              <div class="form-check"><input id="bank" name="paymentMethod" type="radio" class="form-check-input"
                                             value="bank" v-model="form.payment"><label class="form-check-label"
                                                                                        for="bank">무통장 입금</label></div>
            </div>
            <div class="row gy-3">
              <div class="col-md-6"><label for="cc-name" class="form-label">카드 번호</label>
                <input type="text" class="form-control" id="cc-name" placeholder="" v-model="form.cardNumber">
              </div>
            </div>
            <hr class="my-4">
            <button class="w-100 btn btn-primary btn-lg" @click="submit()">결제하기</button>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>