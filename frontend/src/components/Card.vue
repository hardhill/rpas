<template>
  <div class="card" ref="card">
    <div class="card-header">
      <div class="title"><img :src="number+'.png'" alt="Header" /></div>
      <div class="list-point">{{ number }}</div>
      <div class="login">{{ login }}</div>
    </div>
    <div class="card-body">
      <div class="time-info" v-if="!b_cardopen">
        <img src="clock_color.png" alt="clock"/>
        <div class="time-text">{{seconds}}</div>
      </div>
      <div class="sign-info" v-if="!b_cardopen">
        <img src="check_all.png" alt="check_all" /><span>{{ process }}</span>
        <img src="check.png" alt="check" /><span>{{ succproc }} ({{procent}}%)</span>
      </div>
      <div v-if="b_cardopen">
        <ChartTwo :height="180"/>
      </div>
    </div>
    <div class="card-footer" @click="Folding"><span v-html="triangleimg"></span></div>
  </div>
</template>

<script>
    import ChartTwo from "@/components/ChartTwo.vue";
    import axios from "axios";

    export default {
  name: "Card",
    components: {
      ChartTwo
    },
  props: ["number", "login"],
  data: () => {
    return {
      n_succproc: 0,
      n_process: 0,
      n_seconds: 0,
      b_cardopen: false,
      img_1: "&blacktriangledown;",
      img_2: "&blacktriangle;"
    };
  },
  computed: {
    succproc() {
      return this.n_succproc;
    },
    process() {
      return this.n_process;
    },
    seconds() {
      return this.secondsToHms(this.n_seconds);
    },
    procent(){
      return Math.floor((this.n_succproc/(this.n_process>0?this.n_process:1))*100)
    },
    triangleimg() {
      return this.b_cardopen ? this.img_2 : this.img_1
    }
  },
  methods: {
    Folding() {
      //console.log(this.$refs.card.style)
      this.b_cardopen = !this.b_cardopen
      if (this.b_cardopen) {
        this.$refs.card.style.width = "36rem"
        this.$refs.card.style.height = "28rem"
      } else {
        this.$refs.card.style.width = "14rem"
        this.$refs.card.style.height = ""
      }

    },
    getProcessToDay(login) {
      axios
        .get(this.$store.state.url_root+"processtoday?login=" + login)
        .then((resp) => {
          this.n_process = resp.data;
        })
        .catch((err) => {console.log(err)});
    },
    getSuccprocToDay(login) {
      axios
        .get(this.$store.state.url_root+"successtoday?login=" + login)
        .then((response) => {
          this.n_succproc = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getSeconds(login) {
      axios
        .get(this.$store.state.url_root+"secondsday?login=" + login)
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
    this.getSuccprocToDay(this.login)
    this.getProcessToDay(this.login)
    this.getSeconds(this.login)
  },
};
</script>

<style lang="scss" scoped>
.card {
  display: flex;
  flex-direction: column;
    width: 100%;
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
  color: rgb(249, 255, 233);
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
  margin: .5rem 1rem;
  justify-content: space-between;
  align-items: center;
}

.card-body {
  flex-grow: 2;
}

.card-footer {
  cursor: pointer;
  font-size: 1.5rem;
}

</style>