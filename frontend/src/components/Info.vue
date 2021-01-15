<template>
    <div class="inficon">
        <img src="info.png" width="20px"/>
        <div class="info-panel">RPA монитор начал работу с 25 декабря 2020 года.</div>
    </div>
</template>

<script>
    export default {
        name: "Info"
    }
</script>

<style scoped>
    .inficon {
        position: relative;
        cursor: pointer;
    }

    .inficon:hover .info-panel {
        visibility: visible;
    }

    .info-panel {
        position: absolute;
        top: 1.2rem;
        left: -8rem;
        width: 9rem;
        padding: 0.5rem;
        border-radius: 5px;
        background-color: #e0e8f0;
        border: solid 1px #7f7f7f;
        visibility: hidden;
        cursor: default;
    }
</style>