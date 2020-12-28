<template>
  <div class="card">
      <div class="card-header">
      <div class="title"><img src="1.png"></div>
      <div class="list-point">{{number}}</div>
      <div class="login">{{login}}</div>
      
      </div>
      <div class="card-body">
          <div class="time-info"><img src="clock_color.png"/><div class="time-text">3ч 23м 04с</div></div>
          <div class="sign-info">
            <img src="check.png"/><span>203</span>
            <img src="cancel_2.png"/><span>12</span>
          </div>
          
      </div>
      <div class="card-footer"><img src="angle-arrow-down.png"/></div>
  </div>
</template>

<script>

export default {
    name:"Card",
    props:["number","login"],
    data:()=>{
        return {
            nomer:23
        }
    }
}
</script>

<style lang="scss" scope>
.card {
    display: block;
  width: 14rem;
  margin: 1rem 2rem;
  background-color: aliceblue;
}
.card-header{
    position: relative; 
    
 }
.list-point{
    position: absolute;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    font-size: 3em;
    color: rgb(220, 245, 243);
    left:5px;
    top:4px;
    z-index: 3;
}
.login{
    position: absolute;
    color: rgb(218, 193, 193);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    right: 5px;
    top:4px;
}
.title{
    height: 3rem;
    overflow: hidden;
}
.title img{
    min-width: 100%;
    min-height: 100%;
    flex-shrink: 0;
}
.time-info{
    display: flex;
    margin: 1rem;
    justify-content: space-between;
    align-items: center;
}
.sign-info{
    display: flex;
    margin: 1rem;
    justify-content: space-between;
    align-items: center;
}
.card-footer img{
    cursor: pointer;
}
</style>