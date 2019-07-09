const app = new Vue({
  el:"#app",
  data: {
    products: [],
    name: "",
    price: 0,
    feedback:""
  },
  created: function(){
    this.getData();
  },
  methods: {
    getData: async function() {
      let json = await fetch("/api/products", {
        method:"GET"
      })
      .then(function(response) {
            return response.json();
      })
      .then((json) => json)
      .catch(function(error) {
            console.error(error);
      })
      this.products = json.products;
      console.log(this.products);
    },
    addProduct: async function() {
      let payload = {
        name:this.name,
        currentPrice:this.price
      }
      console.log(payload)
      let json = await fetch('/api/products', {
        method:'POST',
        credentials: 'include',
        // mode: 'no-cors',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Accept': 'application/json'
        },
        body: 'name='+payload.name+'&currentPrice='+payload.currentPrice
      })
      .then(function(response) {
          return response.json();
      })
      .then(function(json){
        return json;
      })
      .catch(error => console.log(error))
      this.getData();
    }
  }
})
