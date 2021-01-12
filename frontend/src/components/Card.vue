<template>
  <div class="card">
    <div class="card-header">
      <div class="title"><img src="1.png" alt="Header" /></div>
      <div class="list-point">{{ number }}</div>
      <div class="login">{{ login }}</div>
    </div>
    <div class="card-body">
      <div class="time-info">
        <img src="clock_color.png" alt="clock"/>
        <div class="time-text">{{seconds}}</div>
      </div>
      <div class="sign-info">
        <img src="check.png" alt="check" /><span>{{ succproc }}</span>
        <img src="cancel_2.png" alt="cancel" /><span>{{ errproc }}</span>
      </div>
    </div>
    <div class="card-footer"><img src="angle-arrow-down.png" alt="" /></div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Card",
  props: ["number", "login"],
  data: () => {
    return {
      n_succproc: 0,
      n_errproc: 0,
      n_seconds: 0,
    };
  },
  computed: {
    succproc() {
      return this.n_succproc;
    },
    errproc() {
      return this.n_errproc;
    },
    seconds() {
      return this.secondsToHms(this.n_seconds);
    },
  },
  methods: {
    getErrproc(login) {
      axios
        .get("http://localhost:8098/api/errproc?login=" + login)
        .then((resp) => {
          this.n_errproc = resp.data;
        })
        .catch((err) => {console.log(err)});
    },
    getSuccproc(login) {
      axios
        .get("http://localhost:8098/api/succproc?login=" + login)
        .then((response) => {
          this.n_succproc = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getSeconds(login) {
      axios
        .get("http://localhost:8098/api/seconds?login=" + login)
        .then((response) => {
            
          this.n_seconds = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    secondsToHms(d) {
      d = Number(d);
      var h = Math.floor(d / 3600);
      var m = Math.floor((d % 3600) / 60);
      var s = Math.floor((d % 3600) % 60);

      var hDisplay = h > 0 ? h + (h == 1 ? "ч. " : "ч. ") : "";
      var mDisplay = m > 0 ? m + (m == 1 ? "мин. " : "мин. ") : "";
      var sDisplay = s > 0 ? s + (s == 1 ? "сек." : "сек.") : "";
      return hDisplay + mDisplay + sDisplay;
    },
  },
  mounted() {
      this.getSeconds(this.login)
    this.getErrproc(this.login);
    this.getSuccproc(this.login);
  },
};
</script>

<style lang="scss" scoped>
.card {
  display: block;
  width: 14rem;
  margin: 1rem 2rem;
  background-color: aliceblue;
}
.card-header {
  position: relative;
}
.list-point {
  position: absolute;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  font-size: 3em;
  color: rgb(220, 245, 243);
  left: 5px;
  top: 4px;
  z-index: 3;
}
.login {
  position: absolute;
  color: rgb(218, 193, 193);
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  right: 5px;
  top: 4px;
}
.title {
  height: 3rem;
  overflow: hidden;
}
.title img {
  min-width: 100%;
  min-height: 100%;
  flex-shrink: 0;
}
.time-info {
  display: flex;
  margin: 1rem;
  justify-content: space-between;
  align-items: center;
}
.sign-info {
  display: flex;
  margin: 1rem;
  justify-content: space-between;
  align-items: center;
}
.card-footer img {
  cursor: pointer;
}
</style>