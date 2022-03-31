# Stream Router Service

## This dataflow realized abstract referral system.

API for store a relationships of [NATS subjects] (MQ based on Publisher-Subscriber model).
Each node of unary tree is **Subject** (Similar to Kafka Topic) where parent can listen all children and children of children.
And can block any first level child (Call **FILTER** on the image bellow).
It's dataflow scheme where events move from child to parent:

<img src="proof_of_concept.png" width="800" alt="Data spread"/>


### Endpoints

**GET** ``/api/subjectsTree/{rootName}`` - return all bottom of tree from requested node as list.

_Request:_
```json
{}
```

_Response:_ For parent ``node2`` we're getting all children by parent name.

Statuses

1. OPEN - data stream open.
2. WARN - notification about problem but stream still open.
3. CLOSE - notification obout problem and stream close.

```json
[
  {
    "id": "62445e1f5ea3d56b3fbbcde6",
    "name": "node3",
    "status": "OPEN",
    "route": "node1.node2.node3.*",
    "parentId": "62445e1f5ea3d56b3fbbcde6",
    "createdDate": "2022-03-30T16:41:51.608"
  },
  {
    "id": "62445e1f5ea3d56b3fbbcde6",
    "name": "node4",
    "status": "WARN",
    "route": "node1.node2.node4.*",
    "parentId": "62445e1f5ea3d56b3fbbcde6",
    "createdDate": "2022-03-30T16:41:51.608"
  },
  {
    "id": "62445e1f5ea3d56b3fbbcde6",
    "name": "node5",
    "status": "CLOSE",
    "route": "node1.node2.node3.node5.*",
    "parentId": "62445e1f5ea3d56b3fbbcde6",
    "createdDate": "2022-03-30T16:41:51.608"
  }
]
```

**POST** ``/api/subjectsTree/createNode`` - create new child.

_Request:_
```json
{
  "name": "node3",
  "parentId": "62445e1f5ea3d56b3fbbcde6"
}
```

_Response:_
```json
{
  "id": "62445e1f5ea3d56b3fbbcde6",
  "name": "node3",
  "status": "OK | WARN | STOP",
  "route": "node1.node2.node3.*",
  "parentId": "62445e1f5ea3d56b3fbbcde6",
  "createdDate": "2022-03-30T16:41:51.608"
}
```

**PUT** ``/api/subjectsTree/updateNode/{childId}?status=WARN`` - block\unblock child subtree.

_Request:_
```json
{}
```
_Response:_
```json
{}
```


[NATS subjects]:https://docs.nats.io/nats-concepts/subjects