<template>
    <div class="main">
        <div class="title-control" :style="'background-color: '+backcolor">
            <div class="control" @click="BackView">&blacktriangleleft;</div>
            <div class="title" v-html="title"></div>
            <div class="control" @click="ForwordView">&blacktriangleright;</div>
        </div>
        <div v-if="n_currentview==0">
            <ChartOne @clickupdate="viewtable"/>
        </div>
        <div v-if="n_currentview==1">
            <TableOne/>
        </div>
    </div>
</template>

<script>

    import axios from 'axios'
    import ChartOne from "@/components/ChartOne"
    import TableOne from "@/components/TableOne"

    export default {
        name: "ChartViewer",
        components: {
            ChartOne,
            TableOne
        },
        props: [
            "backcolor"
        ],
        data() {
            return {
                n_maxviewers: 2,
                s_title: "",
                n_allsuccessproc: 1,
                n_spendseconds: 1,
                n_currentview: 0
            }
        },
        computed: {
            title() {
                return this.s_title
            },
            allsuccessproc() {
                return this.n_allsuccessproc
            },
            spendtime() {
                var sec = Number(this.n_spendseconds)
                var d = Math.floor(sec / (24 * 3600))
                var h = Math.floor((sec % (24 * 3600)) / 3600)
                var m = Math.floor((sec % 3600) / 60)
                var s = Math.floor(sec % 60)
                return `${d}д. ${h}ч. ${m}м. ${s}сек.`
            }
        },
        methods: {
            viewtable() {
                this.n_currentview = 1
                this.OnChangeView(this.n_currentview)
            },
            BackView() {
                if (this.n_currentview == 0) {
                    this.n_currentview = this.n_maxviewers - 1
                } else {
                    this.n_currentview--
                }
                this.OnChangeView(this.n_currentview)
            },
            ForwordView() {
                if (this.n_currentview == this.n_maxviewers - 1) {
                    this.n_currentview = 0
                } else {
                    this.n_currentview++
                }
                this.OnChangeView(this.n_currentview)
            },
            OnChangeView() {
                switch (this.n_currentview) {
                    case 0:

                        break
                    case 1:
                        this.GetAllSuccessProc()

                        break
                    case 2:
                        break
                }
            },
            GetAllSuccessProc() {
                axios.get(this.$store.state.url_root + "success20").then((response) => {
                    this.n_allsuccessproc = response.data
                    this.GetSuccessSec20()

                }).catch((err) => {
                    console.log(err)
                })
            },
            GetSuccessSec20() {
                axios.get(this.$store.state.url_root + "seconds20").then((response) => {
                    this.n_spendseconds = response.data
                    this.s_title = "За последние 20 рабочих дней успешно завершенных процессов <b>" + this.allsuccessproc + " (77%)</b>.На это затрачено <b>" + this.spendtime + "</b>"
                }).catch((err) => {
                    console.log(err)
                })
            }
        },
        mounted() {
            this.GetAllSuccessProc()


        }
    }
</script>

<style scoped>
    .main {
        display: block;

    }

    .title-control {
        display: flex;
        background-color: #fff;
    }

    .title {
        text-align: center;
        width: 100%;
        padding: 1rem 1.2rem;
        align-items: center;
    }

    .control {
        width: 3rem;
        font-size: xx-large;
        align-items: center;
        cursor: pointer;
        color: #7f7f7f;
    }

    .control:hover {
        color: #3d4465;
    }
</style>