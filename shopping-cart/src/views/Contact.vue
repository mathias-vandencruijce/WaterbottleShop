<template>
<div class="container">
   <div class="row py-5">
    <div class="col-7 pl-0 ml-0">
      <div class="float-right">
      <h1>CONTACT US</h1>
      <h1 style="background: #e74c3c;content:'';display:block;width: 150px;height: 2px; margin: 20px auto;"></h1>
    </div>

    </div>
     <form v-on:submit.prevent="submitForm" class="">
        <div class="">
           <div class="card">
                <div class="card-img-top">
                    <img src="https://media.discordapp.net/attachments/826083449911115851/827110462952439808/logo.png?width=193&height=189">
               <p class="font-italic py-3 text-">"One sip and you'll be on Cloud nine"</p>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="offset-2 col-9">
                          <div class="form-group">
                            <p class="mb-3">
                              <label class="col-form-label w-100 float-left text-left" for="name">Name</label>
                              <input v-model="form.name" class=" form-control" name="name" min="0" id="name" type="text">
                            </p>
                          </div>
                        </div>
                        <div class="w-100"></div>
                        <div class="offset-2 col-9">
                         <div class="form-group">
                           <p class="mb-3">
                             <label class="col-form-label float-left text-left w-100" for="email">Email</label>
                             <input v-model="form.email" class=" form-control" name="email" id="email" type="text">
                           </p>
                         </div>
                        </div>
                        <div class="w-100"></div>
                         <div class="offset-2 col-9">
                            <div class="form-group">
                              <p class="mb-3">
                                <label class="col-form-label float-left text-left w-100" for="message">Message</label>
                                <textarea v-model="form.message" class=" form-control" name="message" id="message" type="text"/>
                              </p>
                            </div>
                         </div>
                          <div class="offset-3 col-6">
                             <button class="btn btn-primary float-left" @click="submitForm"  value="Submit">Send</button>
                           </div>
                    </div>
                </div>
           </div>
           </div>
     </form>
   </div>
</div>
</template>

<script>
export default {
  name: "AddFeedback",
  data() {
    return {
      form: {
        name: '',
        email: '',
        message: ''
      }
    }
  },
  methods: {
    submitForm() {
      this.$store.dispatch('addFeedback', this.form);

      this.$router.push('/')
    }
  }
}

</script>

<style scoped>

</style>