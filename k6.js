import { check } from "k6";
import http from "k6/http";

export default function() {
    const url = `http://${__ENV.HOST}:8080/urls`;
    const payload = '{"url": "https://github.com/pgjbz"}'
    const properties = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    const res = http.post(url, payload, properties);
    check(res, {
        'is created': r => r.status = 201
    });
}