<template>
  <div class="main">
    <div class="panel">
      <div class="panel-header">
          <AppCaption/>
          <div class="menu">
              <v-icon name="table" class="menu-table" @click="GotoTable"/>
              <InfoHelp/>
          </div>

      </div>
        <ChartViewer backcolor="#eaf6ff"/>

        <div class="grid-view">
          <CardLong :number="index +1" :login="item" v-for="(item,index) in logins" :key="item"/>
        </div>

    </div>
  </div>
</template>

<script>

    import InfoHelp from "@/components/Info.vue"
    import CardLong from "@/components/CardLong"
    import ChartViewer from "@/components/ChartViewer";
    import AppCaption from "@/components/AppCaption";

    export default {
 components:{
   InfoHelp,
   ChartViewer,
     CardLong,
     AppCaption
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
   },
   getDates() {
     this.$store.dispatch('act_dates')
   },
     GotoTable() {
         this.$router.push("table")
     }
 },
 mounted(){
   this.getlogins()
   this.getDates()
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

.grid-view{
  display: flex;

  flex-wrap: wrap;
  justify-content: center;
}

.menu {
    display: flex;
    justify-content: space-around;
    align-items: center;

}

.menu-table {
    color: #004444;
    margin-right: 1.5rem;
    cursor: pointer;
}

</style>
