<script>
    import {Bar} from 'vue-chartjs'
    import axios from "axios";


    export default {
        name: "ChartOne",
        extends: Bar,
        // mixins: [mixins.reactiveProp],
        data: function () {
            return {
                chartdata: {
                    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                    datasets: [
                        {
                            barPercentage: 1,
                            categoryPercentage: 0.5,
                            label: 'Автоматические',
                            backgroundColor: '#62bbfd',
                            data: [40, 39, 10, 40, 39, 80, 40]
                        },
                        {
                            barPercentage: 1,
                            categoryPercentage: 0.5,
                            label: 'Ручные',
                            backgroundColor: '#3D5B96',
                            data: [20, 10, 17, 33, 22, 4, 0]
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        xAxes: [{
                            stacked: true,

                        }],
                        yAxes: [{
                            stacked: true
                        }]
                    },
                    onClick: (event, data) => {
                        try {
                            const itembar = data[0]._index
                            var arrdates = this.$store.state.a_worksdates
                            if (itembar >= 0) {
                                this.$store.commit('set_currentday', arrdates[arrdates.length - itembar - 1])
                                this.$emit('clickupdate')
                            }
                        } catch (e) {

                        }
                    }
                }
            }
        },
        methods: {
            LoadDataProcess() {
                axios.get(this.$store.state.url_root + "alldataprocess").then((response) => {
                    var arr = response.data
                    this.chartdata.labels = []
                    this.chartdata.datasets[0].data = []
                    this.chartdata.datasets[1].data = []
                    arr.forEach((item)=>{
                        this.chartdata.labels.push(item.sworkdate)
                        this.chartdata.datasets[0].data.push(item.successproc)
                        this.chartdata.datasets[1].data.push(item.processes-item.successproc)
                    })
                    //отрисовать
                    this.renderChart(this.chartdata, this.options)
                }).catch((err) => {
                    console.log(err)
                })
            }
        },
        mounted() {
            this.LoadDataProcess()
            this.$store.dispatch('act_dates')

        },
        beforeMount () {
            //this.addPlugin(horizonalLinePlugin)
        },
        watch: {
            data: function () {
                this._chart.destroy();
                //this.renderChart(this.data, this.options);
                this.renderChart();
            }
        }
    }
</script>

<style scoped>

</style>