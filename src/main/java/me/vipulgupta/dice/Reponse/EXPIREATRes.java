// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/proto/response.proto

package me.vipulgupta.dice.Reponse;

/**
 * Protobuf type {@code me.vipulgupta.dice.Reponse.EXPIREATRes}
 */
public final class EXPIREATRes extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:me.vipulgupta.dice.Reponse.EXPIREATRes)
    EXPIREATResOrBuilder {

  public static final int ISCHANGED_FIELD_NUMBER = 1;
  private static final long serialVersionUID = 0L;
  // @@protoc_insertion_point(class_scope:me.vipulgupta.dice.Reponse.EXPIREATRes)
  private static final me.vipulgupta.dice.Reponse.EXPIREATRes DEFAULT_INSTANCE;
  private static final com.google.protobuf.Parser<EXPIREATRes>
      PARSER = new com.google.protobuf.AbstractParser<EXPIREATRes>() {
    @java.lang.Override
    public EXPIREATRes parsePartialFrom(
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
    DEFAULT_INSTANCE = new me.vipulgupta.dice.Reponse.EXPIREATRes();
  }

  private boolean isChanged_ = false;
  private byte memoizedIsInitialized = -1;

  // Use EXPIREATRes.newBuilder() to construct.
  private EXPIREATRes(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EXPIREATRes() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
  getDescriptor() {
    return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_EXPIREATRes_descriptor;
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(me.vipulgupta.dice.Reponse.EXPIREATRes prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  public static me.vipulgupta.dice.Reponse.EXPIREATRes getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  public static com.google.protobuf.Parser<EXPIREATRes> parser() {
    return PARSER;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new EXPIREATRes();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
  internalGetFieldAccessorTable() {
    return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_EXPIREATRes_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            me.vipulgupta.dice.Reponse.EXPIREATRes.class,
            me.vipulgupta.dice.Reponse.EXPIREATRes.Builder.class);
  }

  /**
   * <code>bool isChanged = 1;</code>
   *
   * @return The isChanged.
   */
  @java.lang.Override
  public boolean getIsChanged() {
    return isChanged_;
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
    if (isChanged_ != false) {
      output.writeBool(1, isChanged_);
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
    if (isChanged_ != false) {
      size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, isChanged_);
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
    if (!(obj instanceof me.vipulgupta.dice.Reponse.EXPIREATRes)) {
      return super.equals(obj);
    }
    me.vipulgupta.dice.Reponse.EXPIREATRes other = (me.vipulgupta.dice.Reponse.EXPIREATRes) obj;

    if (getIsChanged()
        != other.getIsChanged()) {
      return false;
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
    hash = (37 * hash) + ISCHANGED_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsChanged());
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
  public com.google.protobuf.Parser<EXPIREATRes> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public me.vipulgupta.dice.Reponse.EXPIREATRes getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

  /**
   * Protobuf type {@code me.vipulgupta.dice.Reponse.EXPIREATRes}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:me.vipulgupta.dice.Reponse.EXPIREATRes)
      me.vipulgupta.dice.Reponse.EXPIREATResOrBuilder {

    private int bitField0_;
    private boolean isChanged_;

    // Construct using me.vipulgupta.dice.Reponse.EXPIREATRes.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
      return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_EXPIREATRes_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
    internalGetFieldAccessorTable() {
      return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_EXPIREATRes_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              me.vipulgupta.dice.Reponse.EXPIREATRes.class,
              me.vipulgupta.dice.Reponse.EXPIREATRes.Builder.class);
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      isChanged_ = false;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
    getDescriptorForType() {
      return me.vipulgupta.dice.Reponse.ResponseProto.internal_static_me_vipulgupta_dice_Reponse_EXPIREATRes_descriptor;
    }

    @java.lang.Override
    public me.vipulgupta.dice.Reponse.EXPIREATRes getDefaultInstanceForType() {
      return me.vipulgupta.dice.Reponse.EXPIREATRes.getDefaultInstance();
    }

    @java.lang.Override
    public me.vipulgupta.dice.Reponse.EXPIREATRes build() {
      me.vipulgupta.dice.Reponse.EXPIREATRes result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public me.vipulgupta.dice.Reponse.EXPIREATRes buildPartial() {
      me.vipulgupta.dice.Reponse.EXPIREATRes result = new me.vipulgupta.dice.Reponse.EXPIREATRes(
          this);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartial0(me.vipulgupta.dice.Reponse.EXPIREATRes result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.isChanged_ = isChanged_;
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
      if (other instanceof me.vipulgupta.dice.Reponse.EXPIREATRes) {
        return mergeFrom((me.vipulgupta.dice.Reponse.EXPIREATRes) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(me.vipulgupta.dice.Reponse.EXPIREATRes other) {
      if (other == me.vipulgupta.dice.Reponse.EXPIREATRes.getDefaultInstance()) {
        return this;
      }
      if (other.getIsChanged() != false) {
        setIsChanged(other.getIsChanged());
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
            case 8: {
              isChanged_ = input.readBool();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
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
     * <code>bool isChanged = 1;</code>
     *
     * @return The isChanged.
     */
    @java.lang.Override
    public boolean getIsChanged() {
      return isChanged_;
    }

    /**
     * <code>bool isChanged = 1;</code>
     *
     * @param value The isChanged to set.
     * @return This builder for chaining.
     */
    public Builder setIsChanged(boolean value) {

      isChanged_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    /**
     * <code>bool isChanged = 1;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearIsChanged() {
      bitField0_ = (bitField0_ & ~0x00000001);
      isChanged_ = false;
      onChanged();
      return this;
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

    // @@protoc_insertion_point(builder_scope:me.vipulgupta.dice.Reponse.EXPIREATRes)
  }

}

