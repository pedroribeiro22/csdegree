const END = Symbol('end');

class DeterministicPushdownAutomation {

    constructor(internal = 'start', external = []) {
        this.internal = internal;
        this.external = external;
        this.halted = false;
        this.recognized = false;
    }

    push(token) {
        this.external.push(token);
        return this;
    }

    pop() {
        this.external.pop();
        return this;
    }

    replace(token) {
        this.external[this.external.length - 1] = token;
        return this;
    }

    top() {
        return this.external[this.external.length - 1];
    }

    hasEmptyStack() {
        return this.external.length == 0;
    }

    transitionTo(internal) {
        this.internal = internal;
        return this;
    }

    recognize() {
        this.recognized = true;
        return this;
    }

    halt() {
        this.halted = true;
        return this;
    }

    consume(token) {
        return this[this.internal](token);
    }

    static evaluate(string) {

        let state = new this();

        for(const token of string) {
            const newState = state.consume(token);

            if(newState == undefined || newState.halted) {
                return false;
            } else if(newState.recognized) {
                return true;
            } else {
                state = newState;
            }
        }

        const finalState = state.consume(END);
        return !!(finalState && finalState.recognized);
    }

}


function test(recognizer, examples) {
    for(const example of examples) {
        console.log(`'${example}' => ${recognizer.evaluate(example)}`);
    }
}


class BalancedParentheses extends DeterministicPushdownAutomation {
    
    start(token) {
        if(token == '(') {
            return this.push(token);
        } else if(token === ')' && this.top() === '(') {
            return this.pop();
        } else if(token === END && this.hasEmptyStack()) {
            return this.recognize();
        }
    }
}

test(BalancedParentheses, [
    '',
    '(',
    '()',
    '()()',
    ')(',
    '()((()()))'
]);