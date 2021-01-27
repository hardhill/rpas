<template>
    <div class="my-table">
        <div style="display: flex; justify-content: flex-end;">
            <select class="form-control select-dates" @change="onChangeDate($event)" v-model="curdate">
                <option v-for="(d,index) in dates" :key="d" :value="d">{{d}}</option>
            </select>
        </div>
        <div style="display:flex; justify-content: center; text-align: center;">
            <table>
                <thead>
                <tr>
                    <td>название процесса</td>
                    <td>ручная обр.</td>
                    <td style="width: 6rem">автомат</td>
                    <td style="width: 4rem">затрачено (ч:м:с.)</td>
                    <td style="width: 4rem">скорость (проц/час)</td>
                </tr>
                </thead>
                <tbody>
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
    </div>
</template>

<script>
    export default {
        name: "TableOne",
        data() {
            return {
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
            dates() {
                return this.$store.state.a_worksdates
            },
            processes() {
                return this.$store.state.processes
            },
            curdate: {
                get: function () {
                    return this.$store.state.s_currentday
                },
                set: function (value) {
                    this.$store.commit('set_currentday', value)
                }

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
            GetDates() {
                this.$store.dispatch('act_worksdates')
            },
            onChangeDate(event) {
                this.$store.commit('set_currentday', event.target.value)
                this.$store.dispatch('act_databyprocess')
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
            getProcName(idx) {
                return this.$store.getters.getProcessName(idx)
            }
        },
        mounted() {
            this.GetDates();
        }
    }
</script>

<style scoped>
    .my-table {
        display: block;
        /*height: 400px;*/
        background-color: #2c3e50;
    }

    .select-dates {
        margin: 0.8rem 1rem;
        font-size: large;
        color: #004444;
    }

    table {
        color: #eaf6ff;
        border: solid 1px #7f7f7f;
        border-collapse: collapse;
        margin: 0.2rem 0.4rem;
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
        background-color: #366691;
    }
</style>