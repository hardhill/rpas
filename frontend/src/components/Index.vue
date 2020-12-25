<template>
  <div class="main">
    <div class="panel">
      <h2>BotX</h2>
      <div class="card">lkjsdlkjsldkj</div>
      <div class="card">lkjsdlkjsldkj</div>
      <div class="card">lkjsdlkjsldkj</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "main",
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
.card {
  width: 16rem;
  
}
</style>
