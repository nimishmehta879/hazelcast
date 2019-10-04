/*
 * Copyright (c) 2008-2019, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.impl.protocol.codec;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.Generated;
import com.hazelcast.client.impl.protocol.codec.builtin.*;
import com.hazelcast.client.impl.protocol.codec.custom.*;

import static com.hazelcast.client.impl.protocol.ClientMessage.*;
import static com.hazelcast.client.impl.protocol.codec.builtin.FixedSizeTypesCodec.*;

/*
 * This file is auto-generated by the Hazelcast Client Protocol Code Generator.
 * To change this file, edit the templates or the protocol
 * definitions on the https://github.com/hazelcast/hazelcast-client-protocol
 * and regenerate it.
 */

/**
 * Query operation to retrieve the current value of the PNCounter.
 * <p>
 * The invocation will return the replica timestamps (vector clock) which
 * can then be sent with the next invocation to keep session consistency
 * guarantees.
 * The target replica is determined by the {@code targetReplica} parameter.
 * If smart routing is disabled, the actual member processing the client
 * message may act as a proxy.
 */
@Generated("ae6494a9f06459b13d3e1a147047efcd")
public final class PNCounterGetCodec {
    //hex: 0x200100
    public static final int REQUEST_MESSAGE_TYPE = 2097408;
    //hex: 0x200101
    public static final int RESPONSE_MESSAGE_TYPE = 2097409;
    private static final int REQUEST_INITIAL_FRAME_SIZE = PARTITION_ID_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int RESPONSE_VALUE_FIELD_OFFSET = RESPONSE_BACKUP_ACKS_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int RESPONSE_REPLICA_COUNT_FIELD_OFFSET = RESPONSE_VALUE_FIELD_OFFSET + LONG_SIZE_IN_BYTES;
    private static final int RESPONSE_INITIAL_FRAME_SIZE = RESPONSE_REPLICA_COUNT_FIELD_OFFSET + INT_SIZE_IN_BYTES;

    private PNCounterGetCodec() {
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class RequestParameters {

        /**
         * the name of the PNCounter
         */
        public java.lang.String name;

        /**
         * last observed replica timestamps (vector clock)
         */
        public java.util.List<java.util.Map.Entry<java.util.UUID, java.lang.Long>> replicaTimestamps;

        /**
         * the target replica
         */
        public com.hazelcast.cluster.Address targetReplica;
    }

    public static ClientMessage encodeRequest(java.lang.String name, java.util.Collection<java.util.Map.Entry<java.util.UUID, java.lang.Long>> replicaTimestamps, com.hazelcast.cluster.Address targetReplica) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        clientMessage.setRetryable(true);
        clientMessage.setAcquiresResource(false);
        clientMessage.setOperationName("PNCounter.Get");
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[REQUEST_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, REQUEST_MESSAGE_TYPE);
        clientMessage.add(initialFrame);
        StringCodec.encode(clientMessage, name);
        EntryListUUIDLongCodec.encode(clientMessage, replicaTimestamps);
        AddressCodec.encode(clientMessage, targetReplica);
        return clientMessage;
    }

    public static PNCounterGetCodec.RequestParameters decodeRequest(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        RequestParameters request = new RequestParameters();
        //empty initial frame
        iterator.next();
        request.name = StringCodec.decode(iterator);
        request.replicaTimestamps = EntryListUUIDLongCodec.decode(iterator);
        request.targetReplica = AddressCodec.decode(iterator);
        return request;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class ResponseParameters {

        /**
         * TODO DOC
         */
        public long value;

        /**
         * last observed replica timestamps (vector clock)
         */
        public java.util.List<java.util.Map.Entry<java.util.UUID, java.lang.Long>> replicaTimestamps;

        /**
         * TODO DOC
         */
        public int replicaCount;
    }

    public static ClientMessage encodeResponse(long value, java.util.Collection<java.util.Map.Entry<java.util.UUID, java.lang.Long>> replicaTimestamps, int replicaCount) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[RESPONSE_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, RESPONSE_MESSAGE_TYPE);
        encodeLong(initialFrame.content, RESPONSE_VALUE_FIELD_OFFSET, value);
        encodeInt(initialFrame.content, RESPONSE_REPLICA_COUNT_FIELD_OFFSET, replicaCount);
        clientMessage.add(initialFrame);

        EntryListUUIDLongCodec.encode(clientMessage, replicaTimestamps);
        return clientMessage;
    }

    public static PNCounterGetCodec.ResponseParameters decodeResponse(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        ResponseParameters response = new ResponseParameters();
        ClientMessage.Frame initialFrame = iterator.next();
        response.value = decodeLong(initialFrame.content, RESPONSE_VALUE_FIELD_OFFSET);
        response.replicaCount = decodeInt(initialFrame.content, RESPONSE_REPLICA_COUNT_FIELD_OFFSET);
        response.replicaTimestamps = EntryListUUIDLongCodec.decode(iterator);
        return response;
    }

}
