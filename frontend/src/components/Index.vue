<template>
  <div class="main">
    <div class="panel">
      <div class="panel-header">
        <div class="caption">BotX <span class="version">0.2 от 14 янв 21г.</span></div>
        <InfoHelp/>
      </div>
        <ChartViewer backcolor="#eaf6ff"/>

        <div class="grid-view">
            <Card :number="index+1" :login="item" v-for="(item,index) in logins" :key="item"/>
      </div>
    </div>
  </div>
</template>

<script>
    import Card from "@/components/Card.vue"
    import InfoHelp from "@/components/Info.vue"

    import ChartViewer from "@/components/ChartViewer";

    export default {
 components:{
   Card,
   InfoHelp,
     ChartViewer
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
  /*height: 100vh;*/
  background-color: rgb(35, 34, 33);
  justify-content: center;
}
.panel {
  display: block;
  width: 640px;
  background-color: rgb(45, 44, 43);

}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem 1rem;
  color: #0086B3;
  background-color: #97bacf;
}

.caption {
  font-size: 1.5rem;
}

.version {
  color: #7f7f7f;
  font-size: 0.8rem;
}
.grid-view{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

</style>
