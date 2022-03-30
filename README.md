# Stream Router Service

## This dataflow realized abstract referral system.

Implementation of [NATS subjects] (MQ Publisher-Subscriber model) data waterfall as described on the image. 

<img src="proof_of_concept.png" width="800" alt="Data spread"/>


The nodes of unary tree is a NATS subject wired with user input (Producers).
Data always move from child to parent nodes with possibility filtering first level children individually. 

### Basic Endpoints

1. **POST** ``/api/subject/create`` - create new child.

_Request:_
```json
{
  "subjectSuffix": "childProducer",
  "parentId": "62445e1f5ea3d56b3fbbcde6"
}
```

_Response:_
```json
{
  "id": "62445e1f5ea3d56b3fbbcde6",
  "subjectSuffix": "parentProducerRoute.childProducerRoute",
  "parentId": "62445e1f5ea3d56b3fbbcde6"
}
```

2. **POST** ``/api/subject/children/{route}`` - return all bottom of tree from requested node.

_Request:_
```json
{}
```

_Response:_
```json
[
  {
    "id": "62445e1f5ea3d56b3fbbcde6",
    "subjectSuffix": "parentProducerRoute.childProducerRoute",
    "parentId": "62445e1f5ea3d56b3fbbcde6"
  }
]
```

3. TODO filters

[NATS subjects]:https://docs.nats.io/nats-concepts/subjects