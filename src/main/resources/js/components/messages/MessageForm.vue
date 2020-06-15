<template>

    <v-layout row>

        <v-text-field
                label="write something"
                outlined
                rounded
                class="ma-3"

                placeholder="Write something"
                v-model="text"
                @keyup.enter="save"

        />
        <v-btn class="ma-4" rounded>
            <v-icon >search</v-icon>
        </v-btn>
        <v-btn @click="save" color="primary" class="ma-4" rounded>
            Save
        </v-btn>
    </v-layout>
</template>

<script>
    import {mapActions} from 'vuex'
    import * as Sentry from '@sentry/browser'
    export default {
        props: [ 'messageAttr'],
        data() {
            return {
                text: '',
                id: null
            }
        },
        watch: {
            messageAttr(newVal, oldVal) {
                this.text = newVal.text
                this.id = newVal.id
            }
        },
        methods: {
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save() {


                const message = {
                    id: this.id,
                    text: this.text }
                if (this.id) {
                    this.updateMessageAction(message)
                } else {
                    this.addMessageAction( message )
                }
                this.text = ''
                this.id = null


            }
        }
    }
</script>

<style>
</style>