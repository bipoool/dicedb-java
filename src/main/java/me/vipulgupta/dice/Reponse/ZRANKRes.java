// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/proto/response.proto

package me.vipulgupta.dice.Reponse;

/**
 * Protobuf type {@code me.vipulgupta.dice.Reponse.ZRANKRes}
 */
public final class ZRANKRes extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:me.vipulgupta.dice.Reponse.ZRANKRes)
    ZRANKResOrBuilder {

  public static final int ELEMENT_FIELD_NUMBER = 2;
  private static final long serialVersionUID = 0L;
  // @@protoc_insertion_point(class_scope:me.vipulgupta.dice.Reponse.ZRANKRes)
  private static final me.vipulgupta.dice.Reponse.ZRANKRes DEFAULT_INSTANCE;
  private static final com.google.protobuf.Parser<ZRANKRes>
      PARSER = new com.google.protobuf.AbstractParser<ZRANKRes>() {
    @java.lang.Override
    public ZRANKRes parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  static {
    DEFAULT_INSTANCE = new me.vipulgupta.dice.Reponse.ZRANKRes();
  }

  private me.vipulgupta.dice.Reponse.ZElement element_;
  private byte memoizedIsInitialized = -1;

  // Use ZRANKRes.newBuilder() to construct.
  private ZRANKRes(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZRANKRes() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
  getDescriptor() {
    return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_ZRANKRes_descriptor;
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(me.vipulgupta.dice.Reponse.ZRANKRes prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  public static me.vipulgupta.dice.Reponse.ZRANKRes getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  public static com.google.protobuf.Parser<ZRANKRes> parser() {
    return PARSER;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZRANKRes();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
  internalGetFieldAccessorTable() {
    return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_ZRANKRes_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            me.vipulgupta.dice.Reponse.ZRANKRes.class,
            me.vipulgupta.dice.Reponse.ZRANKRes.Builder.class);
  }

  /**
   * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
   *
   * @return Whether the element field is set.
   */
  @java.lang.Override
  public boolean hasElement() {
    return element_ != null;
  }

  /**
   * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
   *
   * @return The element.
   */
  @java.lang.Override
  public me.vipulgupta.dice.Reponse.ZElement getElement() {
    return element_ == null ? me.vipulgupta.dice.Reponse.ZElement.getDefaultInstance() : element_;
  }

  /**
   * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
   */
  @java.lang.Override
  public me.vipulgupta.dice.Reponse.ZElementOrBuilder getElementOrBuilder() {
    return element_ == null ? me.vipulgupta.dice.Reponse.ZElement.getDefaultInstance() : element_;
  }

  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) {
      return true;
    }
    if (isInitialized == 0) {
      return false;
    }

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
      throws java.io.IOException {
    if (element_ != null) {
      output.writeMessage(2, getElement());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) {
      return size;
    }

    size = 0;
    if (element_ != null) {
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, getElement());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof me.vipulgupta.dice.Reponse.ZRANKRes)) {
      return super.equals(obj);
    }
    me.vipulgupta.dice.Reponse.ZRANKRes other = (me.vipulgupta.dice.Reponse.ZRANKRes) obj;

    if (hasElement() != other.hasElement()) {
      return false;
    }
    if (hasElement()) {
      if (!getElement()
          .equals(other.getElement())) {
        return false;
      }
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) {
      return false;
    }
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasElement()) {
      hash = (37 * hash) + ELEMENT_FIELD_NUMBER;
      hash = (53 * hash) + getElement().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  @java.lang.Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZRANKRes> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public me.vipulgupta.dice.Reponse.ZRANKRes getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

  /**
   * Protobuf type {@code me.vipulgupta.dice.Reponse.ZRANKRes}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:me.vipulgupta.dice.Reponse.ZRANKRes)
      me.vipulgupta.dice.Reponse.ZRANKResOrBuilder {

    private int bitField0_;
    private me.vipulgupta.dice.Reponse.ZElement element_;
    private com.google.protobuf.SingleFieldBuilderV3<
        me.vipulgupta.dice.Reponse.ZElement, me.vipulgupta.dice.Reponse.ZElement.Builder, me.vipulgupta.dice.Reponse.ZElementOrBuilder> elementBuilder_;

    // Construct using me.vipulgupta.dice.Reponse.ZRANKRes.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
      return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_ZRANKRes_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
    internalGetFieldAccessorTable() {
      return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_ZRANKRes_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              me.vipulgupta.dice.Reponse.ZRANKRes.class,
              me.vipulgupta.dice.Reponse.ZRANKRes.Builder.class);
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      element_ = null;
      if (elementBuilder_ != null) {
        elementBuilder_.dispose();
        elementBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
    getDescriptorForType() {
      return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_ZRANKRes_descriptor;
    }

    @java.lang.Override
    public me.vipulgupta.dice.Reponse.ZRANKRes getDefaultInstanceForType() {
      return me.vipulgupta.dice.Reponse.ZRANKRes.getDefaultInstance();
    }

    @java.lang.Override
    public me.vipulgupta.dice.Reponse.ZRANKRes build() {
      me.vipulgupta.dice.Reponse.ZRANKRes result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public me.vipulgupta.dice.Reponse.ZRANKRes buildPartial() {
      me.vipulgupta.dice.Reponse.ZRANKRes result = new me.vipulgupta.dice.Reponse.ZRANKRes(this);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartial0(me.vipulgupta.dice.Reponse.ZRANKRes result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.element_ = elementBuilder_ == null
            ? element_
            : elementBuilder_.build();
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }

    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }

    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }

    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }

    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }

    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof me.vipulgupta.dice.Reponse.ZRANKRes) {
        return mergeFrom((me.vipulgupta.dice.Reponse.ZRANKRes) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(me.vipulgupta.dice.Reponse.ZRANKRes other) {
      if (other == me.vipulgupta.dice.Reponse.ZRANKRes.getDefaultInstance()) {
        return this;
      }
      if (other.hasElement()) {
        mergeElement(other.getElement());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 18: {
              input.readMessage(
                  getElementFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 18
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     *
     * @return Whether the element field is set.
     */
    public boolean hasElement() {
      return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     *
     * @return The element.
     */
    public me.vipulgupta.dice.Reponse.ZElement getElement() {
      if (elementBuilder_ == null) {
        return element_ == null ? me.vipulgupta.dice.Reponse.ZElement.getDefaultInstance()
            : element_;
      } else {
        return elementBuilder_.getMessage();
      }
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     */
    public Builder setElement(me.vipulgupta.dice.Reponse.ZElement value) {
      if (elementBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        element_ = value;
      } else {
        elementBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     */
    public Builder setElement(
        me.vipulgupta.dice.Reponse.ZElement.Builder builderForValue) {
      if (elementBuilder_ == null) {
        element_ = builderForValue.build();
      } else {
        elementBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     */
    public Builder mergeElement(me.vipulgupta.dice.Reponse.ZElement value) {
      if (elementBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
            element_ != null &&
            element_ != me.vipulgupta.dice.Reponse.ZElement.getDefaultInstance()) {
          getElementBuilder().mergeFrom(value);
        } else {
          element_ = value;
        }
      } else {
        elementBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     */
    public Builder clearElement() {
      bitField0_ = (bitField0_ & ~0x00000001);
      element_ = null;
      if (elementBuilder_ != null) {
        elementBuilder_.dispose();
        elementBuilder_ = null;
      }
      onChanged();
      return this;
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     */
    public me.vipulgupta.dice.Reponse.ZElement.Builder getElementBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getElementFieldBuilder().getBuilder();
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     */
    public me.vipulgupta.dice.Reponse.ZElementOrBuilder getElementOrBuilder() {
      if (elementBuilder_ != null) {
        return elementBuilder_.getMessageOrBuilder();
      } else {
        return element_ == null ?
            me.vipulgupta.dice.Reponse.ZElement.getDefaultInstance() : element_;
      }
    }

    /**
     * <code>.me.vipulgupta.dice.Reponse.ZElement element = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        me.vipulgupta.dice.Reponse.ZElement, me.vipulgupta.dice.Reponse.ZElement.Builder, me.vipulgupta.dice.Reponse.ZElementOrBuilder>
    getElementFieldBuilder() {
      if (elementBuilder_ == null) {
        elementBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            me.vipulgupta.dice.Reponse.ZElement, me.vipulgupta.dice.Reponse.ZElement.Builder, me.vipulgupta.dice.Reponse.ZElementOrBuilder>(
            getElement(),
            getParentForChildren(),
            isClean());
        element_ = null;
      }
      return elementBuilder_;
    }

    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }

    // @@protoc_insertion_point(builder_scope:me.vipulgupta.dice.Reponse.ZRANKRes)
  }

}

