<template>
  <div class="container-lg">
    <h2 class="mt-2">Add a product</h2>
    <form v-on:submit.prevent="submitForm">
        <p class="mb-3">
          <label class="col-form-label w-100 float-left text-left" for="title">Title</label>
          <input v-model="form.title" class="w-75 form-control col-sm-5" name="title" min="0" id="title" type="text">
        </p>
        <p class="mb-3">
          <label class="col-form-label float-left text-left w-100" for="description">Description</label>
          <input v-model="form.description" class="w-75 form-control col-sm-5" name="description" id="description" type="text">
        </p>
        <p class="mb-3">
          <label class="col-form-label float-left text-left w-100" for="thumbnail_url">Thumbnail_url</label>
          <input v-model="form.thumbnail_url" class="w-75 form-control col-sm-5" name="thumbnail_url" id="thumbnail_url" type="text">
        </p>
        <p class="mb-3">
          <label class="col-form-label float-left text-left w-100" for="quantity">Quantity</label>
          <input v-model="form.quantity" class="form-control w-75 col-sm-5" name="quantity" id="quantity" min="0" type="number">
        </p>
        <p class="mb-3">
          <label class="col-form-label float-left text-left w-100" for="price">Price</label>
          <input v-model="form.price" class="form-control w-75 col-sm-5" id="price" name="price" min="0" type="number">
        </p>
        <p>
          <button class="btn btn-primary float-left" @click="submitForm"  value="Submit">Send</button>
        </p>
    </form>
  </div>
</template>

<script>
export default {
  name: "Addproduct",
  data() {
    return {
      form: {
        title: '',
        description: '',
        thumbnail_url: '',
        quantity: '',
        quality: ''
      }
    }
  },
  methods: {
    submitForm() {
      this.$store.dispatch('registerProduct', this.form);

      this.$router.push('/')
    }
  }
}

</script>

<style scoped>

</style>
