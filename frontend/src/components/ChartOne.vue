<script>
    import {Bar, mixins} from 'vue-chartjs'

    export default {
        name: "ChartOne",
        extends: Bar,
        mixins: [mixins.reactiveProp],
        data: () => ({
            chartdata: {
                labels: [],
                datasets: [
                    {
                        label: 'Все процессы',
                        backgroundColor: '#f8c9f3',
                        data: []
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        }),
        methods: {
            LoadDataProcess() {
                axios.get(this.$store.state.url_root + "dataprocess").then((response) => {
                    var arr = response.data

                }).catch((err) => {
                    console.log(err)
                })
            }
        },
        mounted() {

            this.renderChart(this.chartdata, this.options)
        }
    }
</script>

<style scoped>

</style>