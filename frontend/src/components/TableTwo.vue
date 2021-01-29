<template>
    <div class="card-table">
        <table>
            <thead>
            <tr v-if="processes.length>0">
                <td>название процесса</td>
                <td>ручная обр.</td>
                <td style="width: 6rem">автомат</td>
                <td style="width: 4rem">затрачено (ч:м:с.)</td>
                <td style="width: 4rem">скорость (проц/час)</td>
            </tr>
            <tr v-else-if="processes.length==0">
                <td>Нет данных</td>
            </tr>
            </thead>
            <tbody v-if="processes.length>0">
            <tr v-for="(item,index) in processes" :key="item.process">
                <td>{{getProcName(item.process)}}</td>
                <td>{{item.countprocess-item.successproc}}</td>
                <td style="text-align: right">{{item.successproc}}<span> ({{(item.successproc*100/item.countprocess).toFixed(1)}}%)</span>
                </td>
                <td>{{secondsToHms(item.seconds)}}</td>
                <td>{{(3600*item.successproc/item.seconds).toFixed(2)}}</td>
            </tr>
            <tr class="saldo">
                <td></td>
                <td>{{procinfo.errors}}</td>
                <td><span>{{procinfo.success}} ({{procinfo.procent}}%)</span></td>
                <td>{{procinfo.view_sec}}</td>
                <td>{{procinfo.speed}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "TableTwo",
        props: ['login', 'currentdate'],
        data: function () {
            return {
                a_loginprocesses: [],
            }
        },
        computed: {
            processes() {
                return this.a_loginprocesses
            },
            procinfo() {
                var result = {
                    count: 0,
                    errors: 0,
                    success: 0,
                    procent: 0,
                    seconds: 0,
                    view_sec: "",
                    speed: 0
                }
                this.processes.forEach((item) => {
                    result.errors = result.errors + item.countprocess - item.successproc
                    result.success += item.successproc
                    result.count += item.countprocess
                    result.seconds += item.seconds
                })
                result.procent = ((result.success * 100) / result.count).toFixed(1)
                result.view_sec = this.secondsToHms(result.seconds)
                result.speed = ((3600 * result.success) / result.seconds).toFixed(2)
                return result
            }
        },
        methods: {
            getLoginDataProcess(login) {
                var dts = this.$store.state.s_currentday
                axios
                    .get(this.$store.state.url_root + "processesbydateandlogin?dt=" + dts + "&login=" + login)
                    .then((response) => {
                        this.a_loginprocesses = response.data
                    })
                    .catch((err) => {
                        console.log(err);
                    });
            },
            getProcName(idx) {
                return this.$store.getters.getProcessName(idx)
            },
            secondsToHms(d) {
                d = Number(d);
                var h = Math.floor(d / 3600);
                var m = Math.floor((d % 3600) / 60);
                var s = Math.floor((d % 3600) % 60);

                var hDisplay = h > 0 ? h + (":") : "";
                var mDisplay = m > 0 ? m + (":") : "";
                var sDisplay = s > 0 ? s + ("") : "";
                return hDisplay + mDisplay + sDisplay;
            },
        },

        mounted() {
            this.getLoginDataProcess(this.login)
        },
        watch: {
            currentdate: function (newval, oldval) {
                this.getLoginDataProcess(this.login, newval)
            }
        }
    }
</script>

<style scoped>
    .card-table {
        margin: 0.2rem 0.4rem;
    }

    table {
        color: #696969;
        border: solid 1px #7f7f7f;
        border-collapse: collapse;

        width: 100%;
    }

    thead > tr > td {
        padding: 0.4rem 0.6rem;

    }

    td {
        border: solid 1px #7f7f7f;
    }

    tbody > tr > td {
        padding: 0.4rem 0.5rem;
    }

    .saldo {
        background-color: #e0e8f0;
    }
</style>