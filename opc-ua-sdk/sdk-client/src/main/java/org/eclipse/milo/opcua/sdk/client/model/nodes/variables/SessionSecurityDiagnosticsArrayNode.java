/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public class SessionSecurityDiagnosticsArrayNode extends BaseDataVariableNode implements SessionSecurityDiagnosticsArrayType {
    public SessionSecurityDiagnosticsArrayNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<SessionSecurityDiagnosticsNode> getSessionSecurityDiagnosticsNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SessionSecurityDiagnostics").thenApply(SessionSecurityDiagnosticsNode.class::cast);
    }

    public CompletableFuture<SessionSecurityDiagnosticsDataType> getSessionSecurityDiagnostics() {
        return getSessionSecurityDiagnosticsNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, SessionSecurityDiagnosticsDataType.class));
    }

    public CompletableFuture<StatusCode> setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value) {
        return getSessionSecurityDiagnosticsNode().thenCompose(node -> node.setValue(value));
    }
}
