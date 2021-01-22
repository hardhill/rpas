<template>
  <div class="card">
      <div>За последние 20 рабочих дней успешно завершенных процессов.: <span class="bfont">{{successprocmonth}} ({{procentsuccess}}%)</span>
      </div>
      <div>На это затрачено <span class="bfont">{{sucsecmonth}}</span></div>
  </div>
</template>

<script>
    import axios from 'axios'

    export default {
    data:()=>{
        return {
            n_allprocmonth:1,
            n_successmonth: 1,
            n_secondsmonth: 0
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
        },
        sucsecmonth() {
            var sec = Number(this.n_secondsmonth)
            var d = Math.floor(sec / (24 * 3600))
            var h = Math.floor((sec % (24 * 3600)) / 3600)
            var m = Math.floor((sec % 3600) / 60)
            var s = Math.floor(sec % 60)
            return `${d}д. ${h}ч. ${m}м. ${s}сек.`
        }
    },
    methods:{
        GetAllProc(){
            axios.get(this.$store.state.url_root + "allprocs20").then((response) => {
                this.n_allprocmonth = response.data
            }).catch((err)=>{
                console.log(err)
            })
        },
        GetSuccessMonth(){
            axios.get(this.$store.state.url_root + "success20").then((response) => {
                this.n_successmonth = response.data
            }).catch((err)=>{
                console.log(err)
            })
        },
        GetSuccessSecMonth() {
            axios.get(this.$store.state.url_root + "seconds20").then((response) => {
                this.n_secondsmonth = response.data
            }).catch((err) => {
                console.log(err)
            })
        }
    },
    mounted(){
        this.GetAllProc()
        this.GetSuccessMonth()
        this.GetSuccessSecMonth()
    }
}
</script>

<style scoped>
.card{
    display: block;
    background-color: #eaf6ff;
    padding: 1rem 2rem;
}

.bfont {
    font-weight: bold;
    color: #2d5878;
}
</style>