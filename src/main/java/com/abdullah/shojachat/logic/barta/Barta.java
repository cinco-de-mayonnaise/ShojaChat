package com.abdullah.shojachat.logic.barta;

import java.io.Serializable;

/**
 * <h1>Barta</h1>
 *
 * <p>A Barta(বার্তা: means "message" in Bangla) is the object that is used to convey information between client and server.
 * Each Barta of some type contains some fields that signify what it is about, and is the high level object sent and received
 * across the socket. For example, a <code>RecieveMessage_Barta</code>
 * is sent by the server to the client to tell them a new message has been sent to them.</p>
 *
 * <p>As a data class, these Barta contain information that we will use to convey information across client and server.
 * As such, these events should only be constructed by classes that are allowed to generate them
 * and be accessed by classes allowed to handle them.</p>
 *
 * <p>At some point, the client software must translate the Barta into an AppEvent that allows information
 * conveyed by the object to be rendered on screen. For example, once the client recieves a <code>RecieveMessage_Barta</code>,
 * the client should unpack required information from this Barta and create a new <code>RecieveMessage_Event</code> to be
 * processed by the AppEventManager and JavaFX controller at some point. </p>
 *
 */
public class Barta implements Serializable
{

}
