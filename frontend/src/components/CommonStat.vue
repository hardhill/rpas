<template>
  <div class="card">
      <div>За последние 30 дней успешных завершений проц.: <span>{{successprocmonth}} ({{procentsuccess}}%)</span></div>
      <div></div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
    data:()=>{
        return {
            n_allprocmonth:1,
            n_successmonth:1
        }
    },
    computed:{
        successprocmonth(){
            return this.n_successmonth;
        },
        procentsuccess(){
            if(this.n_allprocmonth<1){
                return 0
            }else{
                return Math.floor((this.n_successmonth/this.n_allprocmonth)*100)
            }
        }
    },
    methods:{
        GetAllProc(){
            axios.get(this.$store.state.url_root+"allprocsmonth").then((response)=>{
                this.n_allprocmonth = response.data
            }).catch((err)=>{
                console.log(err)
            })
        },
        GetSuccessMonth(){
            axios.get(this.$store.state.url_root+"successmonth").then((response)=>{
                this.n_successmonth = response.data
            }).catch((err)=>{
                console.log(err)
            })
        },
    },
    mounted(){
        this.GetAllProc()
        this.GetSuccessMonth()

    }
}
</script>

<style scoped>
.card{
    display: block;
    background-color: beige;
    border-radius: 5px;
    padding: 1rem 2rem;
}
</style>