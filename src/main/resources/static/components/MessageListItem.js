// import lifecycleLogger from '../mixins/lifecycle-logger.mixin.js';

export default {
    name: 'MessageListItem',
    // mixins: [lifecycleLogger],
    template: `<li>{{ item.id }} : {{ item.text }} - {{ item.createdAt | datetime }}
    <button @click="deleteClicked">X</button></li>`,
    props: {
        item: {
            type: Object,
            required: true
        }
    },
    methods: {
        deleteClicked() {
            this.$emit('delete');
        }
    }
};