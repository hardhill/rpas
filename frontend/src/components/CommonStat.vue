<template>
  <div>

  </div>
</template>

<script>
import axios from 'axios'
const URL_ROOT = "http://localhost/api/"
export default {
    data:()=>{
        return {
            n_allproc:0
        }
    },
    computed:{
        allproc(){
            return this.n_allproc
        }
    },
    methods:{
        GetAllProc(){
            axios.get(URL_ROOT+"allproc").then((response)=>{
                this.n_allproc = response.data
            }).catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style scoped>

</style>