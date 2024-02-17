export default {
  getNumberFormmated(val) {
    return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
  },
};
