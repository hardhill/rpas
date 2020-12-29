<template>
  <div class="main">
    <div class="panel">
      <h2>BotX</h2>
      <div class="grid-view">
        <Card :number="index+1" :login="i" v-for="(i,index) in logins" :key="i"/>
      </div>
    </div>
  </div>
</template>

<script>
import Card from "@/components/Card.vue"
export default {
 components:{
   Card
 },
 data:function(){
   return {
     
   }
 },
 computed:{
   logins:function(){
     return this.$store.getters.getLogins
   }
 },
 methods:{
   getlogins(){
    this.$store.dispatch('act_logins')
   }
 },
 mounted(){
   this.getlogins()
 }
  
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scope>
.main {
  display: flex;
  width: 100%;
  height: 100vh;
  background-color: rgb(35, 34, 33);
  justify-content: center;
}
.panel {
  display: block;
  width: 640px;
  background-color: rgb(45, 44, 43);
  padding: 2rem 1rem;
}
h2 {
  color: aliceblue;
}
.grid-view{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

</style>
