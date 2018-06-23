<template>
    <gmap-map :center="center" :zoom="9" style="width: auto; height: 100vh">
        <gmap-info-window :options="infoOptions" :position="infoWindowPos" :opened="infoWinOpen" @closeclick="infoWinOpen=false">
            {{infoContent}}
        </gmap-info-window>
        <gmap-cluster>
            <gmap-marker :key="i" v-for="(m,i) in markers" :position="m.position" :clickable="true" @click="toggleInfoWindow(m,i)"></gmap-marker>
        </gmap-cluster>
    </gmap-map>
</template>
<style lang="scss">
    .el-main {
        padding: 0px!important;
    }
</style>
<script>
    export default {
        name: "Map",
        data() {
            return {
                apiUrl: "http://localhost:8090/api/objecten/",
                center: {
                    lat: 52.09,
                    lng: 5.12
                },
                infoContent: '',
                infoWindowPos: {
                    lat: 0,
                    lng: 0
                },
                infoWinOpen: false,
                currentMidx: null,
                //optional: offset infowindow so it visually sits nicely on top of our marker
                infoOptions: {
                    pixelOffset: {
                        width: 0,
                        height: -35
                    }
                },
                markers: [],
                positions: []
            }
        },
        mounted() {
            this.getObjecten();
        },
        methods: {
            getObjecten: function() {
                this.$axios
                    .get(this.apiUrl, {})
                    .then(response => {
                        this.positions = response.data;
                        console.log(response.data);
                        this.positions.forEach((item) => {
                            console.log("foundxcord: ", item.xcord)
                            console.log("found ycord: ", item.ycord)
                            const marker = {
                                lat: item.ycord,
                                lng: item.xcord
                            };
                            this.markers.push({
                                position: marker,
                                infoText: item.locatienummer + " (plaats: " + item.plaats + ")"
                            });
                        });
                    })
                    .catch(e => {
                        this.errors.push(e);
                    });
            },
            mapMarkers(positions) {},
            toggleInfoWindow: function(marker, idx) {
                this.infoWindowPos = marker.position;
                this.infoContent = marker.infoText;
                //check if its the same marker that was selected if yes toggle
                if (this.currentMidx == idx) {
                    this.infoWinOpen = !this.infoWinOpen;
                }
                //if different marker set infowindow to open and reset current marker index
                else {
                    this.infoWinOpen = true;
                    this.currentMidx = idx;
                }
            }
        },
    }
</script>

<style lang="scss">

</style>