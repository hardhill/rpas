<template>
    <div class="card">
        <div class="caption">
            <div class="list-point">{{ number }}</div>
        </div>
        <div class="card-body">
            <div class="card-body-top">
                <div class="card-login">{{login}}</div>
            </div>
            <div class="card-infa">
                <div class="time-info" v-if="!b_cardopen">
                    <img src="clock_color.png" alt="clock"/><span>{{seconds}}</span>
                </div>
                <div class="sign-info" v-if="!b_cardopen">
                    <img src="check_all.png" alt="check_all"/><span>{{ process }}</span>
                    <div style="width: 2rem"/>
                    <img src="check.png" alt="check"/><span> {{ succproc }} ({{procent}}%)</span>
                </div>
                <div class="date-info" v-if="b_cardopen">
                    <v-icon name="calendar"/>
                    <div style="width: .5rem"/>
                    <span>{{curdate}}</span>
                </div>
            </div>
            <TableTwo v-if="b_cardopen" :login="login" :currentdate="curdate"/>
            <div class="card-footer" @click="Folding" v-html="triangleimg"></div>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    import TableTwo from '@/components/TableTwo'

    export default {
        name: "CardLong",
        components: {TableTwo},
        props: ["number", "login"],
        data: () => {
            return {
                n_succproc: 0,
                n_process: 0,
                n_seconds: 0,
                b_cardopen: false,

                img_1: "&blacktriangledown;",
                img_2: "&blacktriangle;",
                o_processes: [{
                    "seconds": 807,
                    "processname": "",
                    "countprocess": 22,
                    "datework": "2021-01-22",
                    "successproc": 12,
                    "process": 237
                }]
            }
        },
        computed: {
            triangleimg() {
                return this.b_cardopen ? this.img_2 : this.img_1
            },
            processes() {
                return this.$store.state.a_loginprocesses
            },
            curdate: {
                get: function () {
                    return this.$store.state.s_currentday
                },
                set: function (value) {
                    this.$store.commit('set_currentday', value)
                }
            },
            succproc() {
                return this.n_succproc;
            },
            process() {
                return this.n_process;
            },
            seconds() {
                return this.secondsToHms(this.n_seconds);
            },
            procent() {
                return Math.floor((this.n_succproc / (this.n_process > 0 ? this.n_process : 1)) * 100)
            },
            triangleimg() {
                return this.b_cardopen ? this.img_2 : this.img_1
            }
        },
        methods: {
            Folding() {
                this.b_cardopen = !this.b_cardopen
            },
            getProcessToDay(login) {
                axios
                    .get(this.$store.state.url_root + "processtoday?login=" + login)
                    .then((resp) => {
                        if (resp.data > 0)
                            this.n_process = resp.data
                        else
                            this.n_process = 0
                    })
                    .catch((err) => {
                        console.log(err)
                    });
            },
            getSuccprocToDay(login) {
                axios
                    .get(this.$store.state.url_root + "successtoday?login=" + login)
                    .then((response) => {
                        this.n_succproc = response.data;
                    })
                    .catch((err) => {
                        console.log(err);
                    });
            },

            getSeconds(login) {
                axios
                    .get(this.$store.state.url_root + "secondsday?login=" + login)
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
    }
</script>

<style scoped>
    .card {
        display: flex;
        float-wrap: none;

        width: 100%;
        background-color: #bac2cb;
        margin-bottom: 1rem;
    }

    .caption {
        position: relative;
        background-color: #0086B3;
        width: 2.5rem;
        align-items: stretch;
    }

    .list-point {
        position: absolute;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        font-size: 3em;
        color: rgb(98 187 253);
        left: 6px;
        top: -8px;
        z-index: 3;
    }

    .card-body {
        width: 100%;
        background-color: #eaf6ff;
    }

    .card-body-top {
        display: flex;
        justify-content: flex-end;
        padding: 0.8rem;
    }

    .card-login {
        color: #7f7f7f;
    }

    .card-infa {
        display: flex;
        padding: 0rem 1rem;
        justify-content: center;
        align-items: center;
    }

    .time-info {
        margin: 0rem 2rem;
        display: flex;
        align-items: center;
    }

    .sign-info {
        display: flex;
        align-items: center;
        margin: 0rem 0rem;
        width: 13rem;
    }

    .date-info {
        display: flex;
        align-items: center;
        color: #7f7f7f;
    }

    img {
        margin-right: 0.5rem;
    }

    .card-footer {
        height: 1.5rem;
        display: flex;
        font-size: x-large;
        justify-content: center;
        cursor: pointer;
    }

</style>